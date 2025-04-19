package me.mine.studying;

import javax.security.auth.Subject;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Task {
    private final int id;
    private final String name;
    private final Subject subject;
    private LocalDateTime startDateTime;
    private Duration duration;
    private TaskStatus status;
    private TaskPriority priority;


    /**
     * Constructor for Task class
     * @param id ID Number
     * @param name Name of task
     * @param subject Subject of task
     * @param startDateTime Starting date/time "MMM. dd HH:mm" format
     * @param durationMinute Duration in minutes
     * @param status Status of the task
     * @param priority Priority of the task
     */
    public Task(int id, String name, Subject subject, String startDateTime, int durationMinute, TaskStatus status, TaskPriority priority) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM. dd HH:mm yyyy", Locale.ENGLISH);
        this.startDateTime = LocalDateTime.parse(startDateTime + " 2025", formatter);
        this.duration = Duration.of(durationMinute, ChronoUnit.MINUTES);
        this.status = status;
        this.priority = priority;
    }

    public Task(int id, String name, Subject subject, String startDateTime, int durationMinute) {
        this(id, name, subject, startDateTime, durationMinute, TaskStatus.NOT_STARTED, TaskPriority.MEDIUM);
    }

    public Task markDone() {
        this.status = TaskStatus.DONE;
        return this;
    }

    public Task inProgress() {
        this.status = TaskStatus.IN_PROGRESS;
        return this;
    }


    public void postPone(Duration duration) {
        this.startDateTime = this.startDateTime.plus(duration);
    }

    public void markAsDone() {
        this.status = TaskStatus.DONE;
    }

    public boolean isOverdue() {
        return this.status == TaskStatus.NOT_STARTED && LocalDateTime.now().isAfter(startDateTime);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Subject getSubject() {
        return subject;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDate getStartDate() {
        return startDateTime.toLocalDate();
    }
    public String getStartDateTimeFormatted() {
        return DateTimeFormatter.ofPattern("HH:mm").format(this.startDateTime);
    }
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Duration getDuration() {
        return duration;
    }
    public void setDuration(Duration durationMinute) {
        this.duration = durationMinute;
    }

    public TaskStatus getStatus() {
        return status;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "startDateTime=" + startDateTime +
                '}';
    }

    public enum TaskStatus {
        NOT_STARTED("Not started", "#f0f0f0", "#6c757d"),
        IN_PROGRESS("In progress",  "#212529", "#ffc107"),
        DONE("Done", "#f0f0f0","#28a745"),
        UNCOMPLETED("Uncompleted", "#f0f0f0" ,"#dc3545");

        private final String name;
        private final String color;
        private final String backgroundColor;

        TaskStatus(String name, String color, String backgroundColor) {
            this.name = name;
            this.color = color;
            this.backgroundColor = backgroundColor;
        }

        public String getBackgroundColor() {
            return backgroundColor;
        }

        public String getName() {
            return name;
        }
        public String getColor() {
            return color;
        }
    }

    public enum TaskPriority {

        HIGH(1),
        MEDIUM(2),
        LOW(3);

        private final int priority;

        TaskPriority(int priority) {
            this.priority = priority;
        }

        public String getName() {
            return this.name().toLowerCase();
        }
        public int getPriority() {
            return priority;
        }
    }

    public enum Subject {
        MATH("Math", LocalDateTime.of(2025, 5, 15, 12, 45)),
        ENGLISH("English", LocalDateTime.of(2025, 5, 8, 12, 45)),
        PSYCHOLOGY("Psychology", LocalDateTime.of(2025, 5, 7, 12, 45)),
        PHYSICS("Physics", LocalDateTime.of(2025, 4, 29, 12, 45)),
        CHEMISTRY("Chemistry", LocalDateTime.of(2025, 5, 16, 12, 45)),
        GERMAN("German", LocalDateTime.of(2025, 5, 6, 12, 45));

        private final String subjectName;
        private final LocalDateTime firstExamDate;

        Subject(String subjectName, LocalDateTime examDate) {
            this.subjectName = subjectName;
            this.firstExamDate = examDate;
        }

        public String getSubjectName() {
            return subjectName;
        }
        public LocalDateTime getFirstExamDate() {
            return firstExamDate;
        }
    }
}
