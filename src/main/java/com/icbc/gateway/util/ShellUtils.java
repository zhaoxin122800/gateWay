package com.icbc.gateway.util;

import com.jcraft.jsch.*;

import java.io.*;

public class ShellUtils {

    /**
     * 创建session
     * @param host 主机名称/ip地址
     * @param user 登陆用户名
     * @param psw  密码
     * @param port 端口
     * @return
     */
    public static Session getSession(String host,String user,String psw,int port){
        JSch jsch=new JSch();
        Session session=null;
        try {
            session = jsch.getSession(user, host, port);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(psw);
            session.connect();
        } catch (JSchException e) {
            System.out.println("连接linux主机失败");
            e.printStackTrace();
        }
        return session;

    }
    /**
     * 得到可以执行命令的连接
     * @param session 连接session
     * @return 可以执行命令的ChannelExec
     */
    public static ChannelExec getChanel(Session session){
        ChannelExec openChannel=null;
        try {
            if(null !=session){
                openChannel = (ChannelExec) session.openChannel("exec");
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return openChannel;
    }
    /**
     * 
     * @param openChannel
     * @param command
     * @return
     */
    public static String getExcRes(ChannelExec openChannel,String command){
        InputStream in =null;
        BufferedReader reader=null;
        StringBuffer result=new StringBuffer();
        try {
            try {
                openChannel.setCommand(command);
                int exitStatus = openChannel.getExitStatus();
                System.out.println(exitStatus);
                openChannel.connect();
                in = openChannel.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                String buf = null;
                while ((buf = reader.readLine()) != null) {
                    result.append(new String(buf.getBytes("gbk"),"UTF-8")+"<br>\r\n");
                }
//reader.close();
            } catch (JSchException e) {
                result.append(e.getMessage());
                e.printStackTrace();
            }
        } catch (IOException e) {
            result.append(e.getMessage());
            e.printStackTrace();
        } /*finally {
//try {
//reader.close();
//} catch (IOException e) {
//e.printStackTrace();
//}
        }*/
        return result.toString();

    }

    public static ChannelShell ChannelShell(Session session) {
        ChannelShell channel = null;
        try {
            if (null != session) {
                channel = (ChannelShell) session.openChannel("shell");
                channel.connect();
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return channel;
    }


    public  static String executeNewFlow(ChannelShell channel, String command) {
        InputStream in =null;
        OutputStream out=null;
        String  msg=null;
        BufferedReader reader=null;
        StringBuffer result=new StringBuffer();
        try {
            in  =  channel.getInputStream();
            out =  channel.getOutputStream();
            out.write(command.getBytes());
            out.flush();
//            reader = new BufferedReader(new InputStreamReader(in));
//            while ((msg =reader.readLine()) !=null ){
//                System.out.println(msg);
//            }
            byte[] tmp=new byte[1024];
            while (true){
                int i=0;
                //线程等待 200ms
                Thread.currentThread().sleep(200);
                while(in.available()>0){
                    i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                }
                System.out.print(new String(tmp, 0, i));
                return new String(tmp, 0, i);
//                if(channel.isClosed()){
//                    if(in.available()>0) continue;
//                    System.out.println("exit-status: "+channel.getExitStatus());
//                    break;
//                }
            }
        }catch (Exception e){
            result.append(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }




    public static void disConnect(Session session,ChannelExec openChannel){
        if(session!=null&&!openChannel.isClosed()){
            openChannel.disconnect();
        }
        if(session!=null&&session.isConnected()){
            session.disconnect();
        }
    }


//    public static void main(String[] args) {
//        Session session=getSession("192.168.230.129", "root", "123456", 22);
//        ChannelExec chanel=getChanel(session);
//        String res=getExcRes(chanel, "cd /home;ls;");
//        System.out.println(res);
//        ChannelExec chanel2=getChanel(session);
//        String ss=getExcRes(chanel2, "ls;");
//        System.out.println(ss);
//    }

}
