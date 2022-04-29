
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.test.Deployment;
import org.flowable.engine.test.FlowableTest;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FlowableTest
public class MyBusinessProcessTest {
    private ProcessEngine processEngine;
    private RuntimeService runtimeService;
    private TaskService taskService;

    @BeforeEach
    void setUp(ProcessEngine processEngine) {
        this.processEngine = processEngine;
        this.runtimeService = processEngine.getRuntimeService();
        this.taskService = processEngine.getTaskService();
    }

    @Test
    @Deployment(resources = "holiday-request.bpmn20.xml")
    void testSimpleProcess() {
        HashMap<String, Object> employeeInfo = new HashMap<>();
        employeeInfo.put("employee", "wangwu1028930");
        //employeeInfo.put()
        runtimeService.startProcessInstanceByKey(
                "holidayRequest", employeeInfo
        );
        Task task = taskService.createTaskQuery().singleResult();
        assertEquals("Approve or reject request", task.getName());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("approved", true);
        taskService.complete(task.getId(), hashMap);
        assertEquals(1, runtimeService
                .createProcessInstanceQuery().count());
    }

}
