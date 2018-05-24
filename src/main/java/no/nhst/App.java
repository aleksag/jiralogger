package no.nhst;

import no.nhst.daily.Parser;
import no.nhst.daily.model.Activity;
import no.nhst.daily.model.Day;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("jira show NCPDN-332");

        List<Day> days = new Parser().parseFile(args[0]);
        List<String[]> worklog = new ArrayList<String[]>();
        for (Day day : days) {
            String date = day.getDate().split("T")[0];
            for (Activity activity : day.getActivities()) {
                String jirakey = getJirakey(activity.getActivity());
                String description = getDescription(activity.getActivity());
                String hours = (activity.getDuration()/60)+"";
                worklog.add(new String[]{jirakey, hours, description, date});
            }
        }

        for (String[] item : worklog) {
            String cmd = createCmd(item);
            execCmd(cmd);
            //System.out.println(cmd);//
            //System.out.println("sleep 0.1");
            //execCmd("jira show NCPDN-332");
        }

    }

    protected static String createCmd(String[] item) {
        //return new String[]{"jira", "worklogadd",item[0],"'" + item[1] + "'",item[2],"-s "+item[3]};
        String jirakey = item[0];
        String duration = item[1];
        String description = item[2];
        String date = item[3];
        return "jira worklogadd \""+ jirakey  +"\" \""+ duration + "\" \""+description+"\"" +" -s "  +"\""+date+"\"";
    }

    public static String execCmd(String cmd) throws java.io.IOException {
        CommandLine cmdLine = CommandLine.parse(cmd);
        DefaultExecutor executor = new DefaultExecutor();
        int exitValue = executor.execute(cmdLine);
        return "";
    }

    private static String processWorklog(String[] item) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("jira", "worklogadd", quot(item[0]), quot(item[1]), quot(item[2]), "-s", quot(item[3]));
        System.out.println(builder.command());
        builder.redirectErrorStream(true);
        String output = IOUtils.toString(builder.start().getInputStream());
        return output;
    }

    private static String quot(String s) {
        return "\"" + s + "\"";
    }
//jira worklogadd NCPDN-332 1h -s "2017-01-29"

    private static String getJirakey(String activity) {
        String key = activity.split(" ")[0];
        if (key.startsWith("GLOBAL") || key.startsWith("NG") || key.startsWith("NCP") || key.startsWith("NHSTADM") || key.startsWith("LAN") || key.startsWith("GDPR")) {
            return key;
        }
        throw new RuntimeException("Cannot parse file, missing jirakey" + key);
    }

    private static String getDescription(String activity) {
        String[] split = activity.split(" ");
        String returnvalue = "";
        for(int i = 1; i < split.length;i++){
            returnvalue = returnvalue + " "+split[i];
        }
        return returnvalue;
    }

    private static Thread watch(final Process process) {
        Thread thread = new Thread() {
            public void run() {
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = null;
                try {
                    while ((line = input.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        return thread;
    }
}


