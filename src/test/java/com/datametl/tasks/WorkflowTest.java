package com.datametl.tasks;

import com.datametl.jobcontrol.Job;
import com.datametl.jobcontrol.JobManager;
import com.datametl.jobcontrol.JobState;
import com.datametl.jobcontrol.SubJob;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.Vector;

import static org.junit.Assert.*;

/**
 * Created by mspallino on 2/24/17.
 */
public class WorkflowTest {

    @Test
    public void run() throws Exception {
        String emptyPacketData = "{\n" +
                "\t\"source\": {\n" +
                "\t\t\"host_ip\": \"\",\n" +
                "\t\t\"host_port\": 1234,\n" +
                "\t\t\"path\": \"MOCK_DATA.xml\",\n" +
                "\t\t\"file_type\": \"xml\"\n" +
                "\t},\n" +
                "\t\"rules\": {\n" +
                "\t\t\"transformations\": {\n" +
                "\t\t\t\"transform1\": {\n" +
                "\t\t\t\t\"source_column\": \"id\",\n" +
                "\t\t\t\t\"new_field\": \"tester3\",\n" +
                "\t\t\t\t\"transform\": \"ADD 3\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"transform2\": {\n" +
                "\t\t\t\t\"source_column\": \"id\",\n" +
                "\t\t\t\t\"new_field\": null,\n" +
                "\t\t\t\t\"transform\": \"MULT 2\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"transform3\": {\n" +
                "\t\t\t\t\"source_column\": \"id\",\n" +
                "\t\t\t\t\"new_field\": \"desty4\",\n" +
                "\t\t\t\t\"transform\": \"MULT 4\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"mappings\": {\n" +
                "\t\t\t\"tester1\": \"desty2\",\n" +
                "\t\t\t\"tester2\": \"desty1\"\n" +
                "\t\t},\n" +
                "\t\t\"filters\": {\n" +
                "\t\t\t\"filter1\": {\n" +
                "\t\t\t\t\"source_column\": \"tester4\",\n" +
                "\t\t\t\t\"filter_value\": \"tester\",\n" +
                "\t\t\t\t\"equality_test\": \"eq\"\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"destination\": {\n" +
                "\t\t\"host_ip\": \"\",\n" +
                "\t\t\"host_port\": 1234,\n" +
                "\t\t\"username\": \"\",\n" +
                "\t\t\"password\": \"\",\n" +
                "\t\t\"storage_type\": \"\"\n" +
                "\t},\n" +
                "\t\"data\": {\n" +
                "\t\t\"source_header\": \"\",\n" +
                "\t\t\"destination_header\": [\"tester1\", \"tester2\", \"tester3\", \"desty4\"],\n" +
                "\t\t\"contents\": [],\n" +
                "\t}\n" +
                "}";
        JSONObject etlPacket = new JSONObject(emptyPacketData);
        JobManager manager = new JobManager();
        UUID jobId = manager.addJob(etlPacket);

        manager.startJob(jobId);
        manager.stopJob(jobId);

        Thread.sleep(2000);
        assertEquals(manager.getJobState(jobId), JobState.SUCCESS);
    }

}