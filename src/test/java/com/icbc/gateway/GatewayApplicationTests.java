package com.icbc.gateway;

import com.icbc.gateway.util.RemoteExecuteCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayApplicationTests {

	@Test
	public void contextLoads() {
		RemoteExecuteCommand rec = new RemoteExecuteCommand("192.168.200.128","root","123456");
		//System.out.println(rec.execute("ifconfig"));
	//	String ps=rec.execute("mkdir /home/test.txt");
	//	System.out.println(ps);
		//rec.executeSuccess("ifconfig");
		String ps=rec.executeSuccess("sh /home/start1.sh");
		System.out.println(ps);

	}

}
