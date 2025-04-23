package me.mine.studying;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
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
                new Task(11, "Physics A4 Continued", Task.Subject.PHYSICS, "Apr. 17 21:40", 40),


/*
         .                    .        .   ___
    /|    \,___, .___  `  |       /|  /   \
   /  \   |    \ /   \ |  |        |  `---|
  /---'\  |    | |   ' |  |        |      |
,'      \ |`---' /     / /\__     _|_ `---'
          \
 */
                new Task(12, "Physics Fields Review", Task.Subject.PHYSICS, "Apr. 19 17:00", 60).markDone(),
                new Task(13, "Physics Exam Qs (Fields & Kinetics)", Task.Subject.PHYSICS, "Apr. 19 20:10", 60).markDone(),
                new Task(14, "German Writings review / review words", Task.Subject.GERMAN, "Apr. 19 21:30", 55).markDone(),
                new Task(15, "Physics Exam Qs A1~3", Task.Subject.PHYSICS, "Apr. 19 22:40", 30).markDone(),

/*
      .o.                            o8o  oooo        .oooo.     .oooo.
     .888.                           `"'  `888      .dP""Y88b   d8P'`Y8b
    .8"888.     oo.ooooo.  oooo d8b oooo   888            ]8P' 888    888
   .8' `888.     888' `88b `888""8P `888   888          .d8P'  888    888
  .88ooo8888.    888   888  888      888   888        .dP'     888    888
 .8'     `888.   888   888  888      888   888      .oP     .o `88b  d88'
o88o     o8888o  888bod8P' d888b    o888o o888o     8888888888  `Y8bd8P'
                 888
                o888o
 */
                new Task(16, "Math Nov. 22 Paper 1", Task.Subject.MATH, "Apr. 20 11:00",120).markDone(),
                new Task(17, "Math Nov. 22 Paper 1 Review", Task.Subject.MATH, "Apr. 20 13:15", 15),
                new Task(18, "Math Nov. 22 Paper 2", Task.Subject.MATH, "Apr. 20 14:30", 90),
                new Task(19, "Math Nov. 22 Paper 3", Task.Subject.MATH, "Apr. 20 18:20", 60),
/*  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.    .----------------.  .----------------.  */
/* | .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |  | .--------------. || .--------------. | */
/* | |      __      | || |   ______     | || |  _______     | || |     _____    | || |   _____      | |  | |    _____     | || |    ______    | | */
/* | |     /  \     | || |  |_   __ \   | || | |_   __ \    | || |    |_   _|   | || |  |_   _|     | |  | |   / ___ `.   | || |   / ____ `.  | | */
/* | |    / /\ \    | || |    | |__) |  | || |   | |__) |   | || |      | |     | || |    | |       | |  | |  |_/___) |   | || |   `'  __) |  | | */
/* | |   / ____ \   | || |    |  ___/   | || |   |  __ /    | || |      | |     | || |    | |   _   | |  | |   .'____.'   | || |   _  |__ '.  | | */
/* | | _/ /    \ \_ | || |   _| |_      | || |  _| |  \ \_  | || |     _| |_    | || |   _| |__/ |  | |  | |  / /____     | || |  | \____) |  | | */
/* | ||____|  |____|| || |  |_____|     | || | |____| |___| | || |    |_____|   | || |  |________|  | |  | |  |_______|   | || |   \______.'  | | */
/* | |              | || |              | || |              | || |              | || |              | |  | |              | || |              | | */
/* | '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |  | '--------------' || '--------------' | */
/*  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'    '----------------'  '----------------'  */
                /*
                60 min - 15 min
                 */

                // Session #1
                new Task("Physics Pre-mock P1b", Task.Subject.PHYSICS, "Apr. 23 07:20", 10),
                new Task("Physics Flashcards 10 key Qs", Task.Subject.PHYSICS, "Apr. 23 07:30", 5).markDone(),
                new Task("Physics A2 Review Qs (x2)", Task.Subject.PHYSICS, "Apr. 23 07:35", 65).markDone(),

                // Session #2
                new Task("Physics B1~4 Review Qs", Task.Subject.PHYSICS, "Apr. 23 08:55", 60).markDone(),

                // Session #3
                new Task("Physics C1 timed quiz", Task.Subject.PHYSICS, "Apr. 23 10:10", 5).markDone(),
                new Task("Physics C1 SHM Review Qs", Task.Subject.PHYSICS, "Apr. 23 10:15", 25).markDone(),
                new Task("Physics C2~3 Review Qs", Task.Subject.PHYSICS, "Apr. 23 10:40", 30).markDone(),

                // Session #4
                new Task("Physics C4 Review Qs", Task.Subject.PHYSICS, "Apr. 23 11:25", 60).inProgress(),

                // Lunch

                // Session #5
                new Task("Physics Nuclear Form Qs", Task.Subject.PHYSICS, "Apr. 23 13:45", 40),
                new Task("Physics Photoelectric effect Qs", Task.Subject.PHYSICS, "Apr. 23 14:25", 20),

                // Session #6
                new Task("Physics E3 Decay Qs", Task.Subject.PHYSICS, "Apr. 23 15:00", 60),

                // 45 - 15

                // Session #7
                new Task("Astrophysics Qs", Task.Subject.PHYSICS, "Apr. 23 16:30", 30),
                new Task("Physics E5 Review Qs", Task.Subject.PHYSICS, "Apr. 23 17:10", 50),


                // Session #8
                new Task("Review Electromagnetism Extension Sheet", Task.Subject.PHYSICS, "Apr. 23 18:10", 25),


                // DINNER

                // Session #9
                new Task("Physics D1~4 Practice Qs", Task.Subject.PHYSICS, "Apr. 23 20:20", 45),

                // Session #10
                new Task("Physics D4 Example Exam Qs", Task.Subject.PHYSICS, "Apr. 23 21:20", 45),

                // Session #11
                new Task("Specimen Paper 2", Task.Subject.PHYSICS, "Apr. 23 22:20", 150)



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

            long totalMinutes = tasksOfDate.stream().map(t -> t.getDuration().toMinutes()).mapToLong(Long::longValue).sum();
            long doneMinutes = tasksOfDate.stream().filter(t -> t.getStatus().equals(Task.TaskStatus.DONE) || t.getStatus().equals(Task.TaskStatus.IN_PROGRESS)).map(t -> t.getDuration().toMinutes()).mapToLong(Long::longValue).sum();
            System.out.println();
            System.out.println(totalMinutes);
            System.out.println(doneMinutes);

            int donePercentage = Math.round((float) (100.0 * doneMinutes / totalMinutes));
            System.out.println(donePercentage);
//            md.append("> #### Day Progress: [ ").append("▰".repeat(donePercentage)).append("▱".repeat(10-donePercentage)).append(" ]\n\n");
            md.append("![](https://geps.dev/progress/").append((donePercentage)).append(")\n\n");
            md.append("| Task | Subject | Start Time | End Time | Duration | Status |\n");
            md.append("|------|:-------:|:----------:|:--------:|:--------:|:------:|\n");

            for (Task task : tasksOfDate) {
                md.append(String.format("| %-33s | %-17s | %-17s | %-17s | %-10s | %s |\n",
                        task.getName(),
                        task.getSubject().getSubjectName(),
                        task.getStartDateTimeFormatted(),
                        DateTimeFormatter.ofPattern("HH:mm").format(task.getStartDateTime().plus(task.getDuration())),
                        formatDuration(task.getDuration()),
                        formatString(task.getStatus().getName(), "padding:4px 10px;border-radius: 25px;background-color:" + task.getStatus().getBackgroundColor() + ";color:" + task.getStatus().getColor())
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