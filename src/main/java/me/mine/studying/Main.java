package me.mine.studying;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>(Arrays.asList(
                new Task(1, "2024 Nov. SAQ write", Task.Subject.PSYCHOLOGY, "Apr. 15 23:00", 40),

/*
    _       ____      ____                 _             _     __
U  /"\  u U|  _"\ uU |  _"\ u     ___     |"|           /"| U /"/_ u
 \/ _ \/  \| |_) |/ \| |_) |/    |_"_|  U | | u       u | |u\| '_ \/
 / ___ \   |  __/    |  _ <       | |    \| |/__       \| |/ | (_) |
/_/   \_\  |_|       |_| \_\    U/| |\\u  |_____|       |_|   \___/
 \\    >>  ||>>_     //   \\_.-,_|___|_,-.//  \\      _//<,-,_// \\_
(__)  (__)(__)__)   (__)  (__)\_)-' '-(_/(_")("_)    (__)(_/(__) (__)
*/
                new Task(2, "2024 Nov. P1 whole write", Task.Subject.ENGLISH, "Apr. 16 13:20", 75),
                new Task(3, "2024 Nov. P2 whole write", Task.Subject.ENGLISH, "Apr. 16 14:50", 105),
                new Task(4, "Physics A1~5 Review", Task.Subject.PHYSICS, "Apr. 16 16:00", 80),
                new Task(5, "Physics C1~5 Review", Task.Subject.PHYSICS, "Apr. 16 17:50", 80),

/*
 █████╗ ██████╗ ██████╗ ██╗██╗          ██╗███████╗
██╔══██╗██╔══██╗██╔══██╗██║██║         ███║╚════██║
███████║██████╔╝██████╔╝██║██║         ╚██║    ██╔╝
██╔══██║██╔═══╝ ██╔══██╗██║██║          ██║   ██╔╝
██║  ██║██║     ██║  ██║██║███████╗     ██║   ██║
╚═╝  ╚═╝╚═╝     ╚═╝  ╚═╝╚═╝╚══════╝     ╚═╝   ╚═╝
*/
                new Task(6, "Physics A1~2 Review", Task.Subject.PHYSICS, "Apr. 17 14:15", 80).markDone(),
                new Task(7, "Physics A3 Review", Task.Subject.PHYSICS, "Apr. 17 15:40", 20).markDone(),
                new Task(8, "Physics A4 Review", Task.Subject.PHYSICS, "Apr. 17 16:10", 50).markDone(),
                new Task(9, "Physics Fields Review", Task.Subject.PHYSICS, "Apr. 17 18:15", 60),
                new Task(10, "Physics Fields Continued", Task.Subject.PHYSICS, "Apr. 17 20:55", 40),
                new Task(11, "Physics A4 Continued", Task.Subject.PHYSICS, "Apr. 17 21:40", 40)
        ));

        tasks.forEach(task -> {
            if (task.isOverdue()) task.setStatus(Task.TaskStatus.UNCOMPLETED);
        });
        tasks.sort(Comparator.comparing(Task::getStartDateTime));
        printTasks(tasks);
    }

    private static String formatString(String string, String css) {
        return String.format("<span style='%s'>%s</span>", css, string);
    }

    private static String formatDuration(Duration duration) {
        long totalMinutes = duration.toMinutes();
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;

        StringBuilder sb = new StringBuilder();

        if (hours > 0) {
            sb.append(hours).append(" hr");
            if (hours > 1) {
                sb.append("s");  // plural
            }
            if (minutes > 0) {
                sb.append(" ");
            }
        }

        if (minutes > 0 || hours == 0) {
            sb.append(minutes).append(" min");
            if (minutes != 1) {
                sb.append("s");  // plural
            }
        }

        return sb.toString();
    }

    private static void printTasks(ArrayList<Task> tasks) {
        StringBuilder md = new StringBuilder();

        tasks.stream().collect(Collectors.groupingBy(Task::getStartDate, TreeMap::new, Collectors.toList())).forEach((localDate, tasksOfDate) -> {
            md.append("## 📅 Study Schedule ").append(localDate.format(DateTimeFormatter.ofPattern("MMM. dd"))).append("\n\n");
            md.append("| Task | Subject | Start Time | End Time | Duration | Status | Priority |\n");
            md.append("|------|:-------:|:----------:|:--------:|:--------:|:------:|:--:|\n");

            for (Task task : tasksOfDate) {
                md.append(String.format("| %-33s | %-17s | %-17s | %-17s | %-10s | %s | %s |\n",
                        task.getName(),
                        task.getSubject().getSubjectName(),
                        task.getStartDateTimeFormatted(),
                        DateTimeFormatter.ofPattern("HH:mm").format(task.getStartDateTime().plus(task.getDuration())),
                        formatDuration(task.getDuration()),
                        formatString(task.getStatus().getName(), "padding:4px 10px;border-radius: 25px;background-color:" + task.getStatus().getBackgroundColor() + ";color:" + task.getStatus().getColor()),
                        task.getPriority().getName()
                ));
            }
        });




        try {
            Files.writeString(Path.of("schedule.md"), md.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}