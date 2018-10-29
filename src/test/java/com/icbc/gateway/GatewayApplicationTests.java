package com.icbc.gateway;

import com.icbc.gateway.pojo.GTH;
import com.icbc.gateway.service.GthService;
import com.icbc.gateway.util.RemoteExecuteCommand;
import com.icbc.gateway.util.SSH2Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayApplicationTests {

	@Autowired
	private GthService gthService;

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
	@Test
	public void test() throws Exception {
		Map<String,List<GTH>> map=gthService.gthAll();
		for (List<GTH> value:map.values()){
			for(GTH li:value){
				li.getId();
			}
		}
	}
	@Test
	public void  test2() throws Exception {
		SSH2Util ssh2Util= new SSH2Util("192.168.200.128", "root","123456", 22);
		ssh2Util.putFile("D:","logmock.sh","/home");
	}

	@Test
	public void test3() throws Exception {
	Map<String,List<GTH>>	map	=gthService.gthAll();
	for(List<GTH> value : map.values()){
		for(GTH gth:value){
		String[]	st=gth.getStartShell().split("/");
		//	System.out.println(st[1]+"+"+st[2]);
		}
	}
	}
}
