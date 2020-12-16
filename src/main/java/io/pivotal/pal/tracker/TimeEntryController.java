package io.pivotal.pal.tracker;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);

        return new ResponseEntity<>(timeEntry, HttpStatus.CREATED);

    }

    @GetMapping(value="/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        var timeEntry = timeEntryRepository.find(id);
        if (timeEntry == null) {
            return new ResponseEntity<>(timeEntry, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        }
    }


    @GetMapping(value="/time-entries")
        public ResponseEntity<List<TimeEntry>> list() {
        HttpHeaders headers = new HttpHeaders();
        List<TimeEntry> timeEntryList = new ArrayList<>();
        timeEntryList = timeEntryRepository.list();
        return new ResponseEntity<List<TimeEntry>>(timeEntryList, HttpStatus.OK);
    }

    @PutMapping(value="/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeentry) {
        var timeEntry = timeEntryRepository.update(id, timeentry);
        if (timeEntry == null) {
            return new ResponseEntity<>(timeEntry, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        }
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
