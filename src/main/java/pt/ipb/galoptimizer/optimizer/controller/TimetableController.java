package pt.ipb.galoptimizer.optimizer.controller;

import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galoptimizer.optimizer.model.Lesson;
import pt.ipb.galoptimizer.optimizer.model.Timetable;
import pt.ipb.galoptimizer.optimizer.service.ClassroomService;
import pt.ipb.galoptimizer.optimizer.service.LessonService;
import pt.ipb.galoptimizer.optimizer.service.TimeslotService;

import java.util.UUID;
import java.util.concurrent.ExecutionException;


@Controller
@CrossOrigin
@RequestMapping("/timetables")
public class TimetableController {
    private static final UUID problemId = UUID.randomUUID();

    @Autowired
    private SolverManager<Timetable, UUID> solverManager;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private TimeslotService timeslotService;

    @PostMapping("/solve")
    public ResponseEntity<Object> solve() {
        Timetable problem = new Timetable(timeslotService.findAll(), classroomService.findAll(), lessonService.findAll());

        SolverJob<Timetable, UUID> solverJob = solverManager.solve(problemId, problem);
        Timetable solution;

        try {
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Solving failed.", e);
        }

        for(Lesson lesson: solution.getLessons()) {
            lessonService.update(lesson);
        }

        return ResponseEntity.ok().body(solution);
    }

    @PostMapping("/stop")
    public ResponseEntity<Object> stop() {
        solverManager.terminateEarly(problemId);
        return ResponseEntity.ok().build();
    }
}
