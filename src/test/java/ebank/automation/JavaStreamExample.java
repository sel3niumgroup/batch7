package ebank.automation;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.testng.annotations.Test;
import utils.Pojo;
import utils.TestUtils;

public class JavaStreamExample {

	@Test
	public void JavaStreamUsage() throws IOException {
		String dataFile = "./testData/DB.csv";
		Map<String, List<Pojo>> grouped = TestUtils.testCasesGroupByPolicyNo(dataFile);
		List<Entry<String,List<Pojo>>> list = grouped.entrySet().stream().collect(Collectors.toList());
		
		for (int i = 0; i < list.size(); i++) {
			List<Pojo> l = list.get(i).getValue();
			String policyNo = l.get(0).getPolicy_No();
			System.out.println(policyNo);
		}
	}
}
