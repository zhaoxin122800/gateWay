package com.icbc.gateway;

import com.icbc.gateway.service.GthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayApplicationTests {

	@Autowired
	private GthService gthService;
	@Test
	public void test(){

	}
//	@Test
//	public void contextLoads() {
//		RemoteExecuteCommand rec = new RemoteExecuteCommand("192.168.200.144","root","123456");
//		//System.out.println(rec.execute("ifconfig"));
//	//	String ps=rec.execute("mkdir /home/test.txt");
//	//	System.out.println(ps);
//		//rec.executeSuccess("ifconfig");
//		String ps=rec.executeSuccess("sh /home/start1.sh");
//		System.out.println(ps);
//
//	}
//	@Test
//	public void test() throws Exception {
//		Map<String,List<GTH>> map=gthService.gthAll();
//		for (List<GTH> value:map.values()){
//			for(GTH li:value){
//				li.getId();
//			}
//		}
//	}
//	@Test
//	public void  test2() throws Exception {
//		SSH2Util ssh2Util= new SSH2Util("192.168.200.128", "root","123456", 22);
//		ssh2Util.putFile("D:","logmock.sh","/home");
//	}

//	@Test
//	public void test3() throws Exception {
//	Map<String,List<GTH>>	map	=gthService.gthAll();
//	for(List<GTH> value : map.values()){
//		for(GTH gth:value){
//		String[]	st=gth.getStartShell().split("/");
//		//	System.out.println(st[1]+"+"+st[2]);
//		}
//	}
//	}
//	@Test
//	public void test4(){
//		SSH2Util ssh = new SSH2Util("192.168.200.144", "root", "123456" , 22);
//		 try {
//		 String rs = ssh.runCommand("cd /home");
//		// String rs1 = ssh.runCommand("ll");
//		 } catch (Exception e) {
//		 e.printStackTrace();
//		 }
//	}

	@Test
	public void test2(){
//		Session session= ShellUtils.getSession("192.168.200.150", "root", "123456", 22);
//		ChannelExec chanel=ShellUtils.getChanel(session);
//		String res=ShellUtils.getExcRes(chanel, "cd /home;ls;");
//		System.out.println(res);
//		System.out.println("---------------------------------");
//		ChannelExec chanel2=ShellUtils.getChanel(session);
//		String ss=ShellUtils.getExcRes(chanel2, "ls;");
//		System.out.println(ss);
	}

	@Test
	public void test3() {
//		Session session= ShellUtils.getSession("192.168.200.147", "root", "123456", 22);
//		ChannelShell channelShell = ShellUtils.ChannelShell(session);
//		List<String> li = new ArrayList<>();
//		li.add("ls");
//		li.add("cd /home");
//		li.add("ls");
//		for(String str:li){
//			String res = ShellUtils.executeNewFlow(channelShell,str+" \n\r");
//				System.out.println(res);
//			}
//		}
	}
	}

