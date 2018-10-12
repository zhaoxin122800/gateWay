package com.icbc.gateway.controller;

//@RestController
//@RequestMapping("gth")
//public class GthController {
//    @Autowired
//    private  GthService gthService;
//
//
//    @GetMapping("/test")
//    public ModelAndView index(){
//
//        return new ModelAndView("/test");
//    }
//
//    //展示所有
//    @RequestMapping(value ="/gthAll", method = RequestMethod.GET)
//   public List<GTH> gthAll() throws Exception {
//
//       return gthService.gthAll();
//   }
//
//    /**
//     * @param startShell  启动
//     * @throws IOException  IO流异常 抛出
//     */
//    @RequestMapping(value = "/startShell",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
//    public Result startShell(String startShell) throws IOException {
//        try {
//            //获取logger  日志
//            Logger logger =LoggerFactory.getLogger(getClass());
//
//            //获得shell脚本地址
//            String shPath=startShell;
//            //执行脚本
//            Process ps = Runtime.getRuntime().exec(shPath);
//            //执行结果 并返回
//            int exitValue =  ps.waitFor();
//            // 如果 执行结果 不等于0  返回错误信息
//            if(0 != exitValue)
//                logger.info("call shell failed. error code is :" + exitValue);
//
//            //设置接收结果集
//            String result = null;
//
//            //获取shell返回流
//            BufferedInputStream in =new BufferedInputStream(ps.getInputStream());
//            //字符流转换字节流
//            BufferedReader br =new BufferedReader(new InputStreamReader(in));
//            //循环字节流 获得结果
//            String lineStr;
//            while ((lineStr = br.readLine()) != null){
//                result=lineStr;
//            }
//            //关流
//            br.close();
//            in.close();
//            //打印返回结果
//            logger.info("脚本返回结果如下："+result);
//            //  System.out.println("脚本返回结果如下："+result);
//
//            return new Result(true,"启动成功");
//        }catch (Exception e) {
//            e.printStackTrace();
//            return new Result(false,"启动失败");
//        }
//    }
//
//    //重启
//    @ResponseBody
//    @RequestMapping(value = "/resetShell",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
//    public Result resetShell(String resetShell) throws Exception{
//        try {
//            //获取logger  日志
//            Logger logger =LoggerFactory.getLogger(getClass());
//
//            String shPath = resetShell;
//            Process ps = Runtime.getRuntime().exec(shPath);
//            //执行结果 并返回
//            int exitValue =  ps.waitFor();
//            // 如果 执行结果 不等于0  返回错误信息
//            if(0 != exitValue)
//                logger.info("call shell failed. error code is :" + exitValue);
//
//            //设置接收结果集
//            String result = null;
//
//            //获取shell返回流
//            BufferedInputStream in =new BufferedInputStream(ps.getInputStream());
//            //字符流转换字节流
//            BufferedReader br =new BufferedReader(new InputStreamReader(in));
//            //循环字节流 获得结果
//            String lineStr;
//            while ((lineStr = br.readLine()) != null){
//                result=lineStr;
//            }
//            //关流
//            br.close();
//            in.close();
//            //打印返回结果
//            logger.info("脚本返回结果如下："+result);
//            //  System.out.println("脚本返回结果如下："+result);
//
//
//
//            return new Result(true,"重启成功");
//        }catch (Exception e){
//            return new Result(false,"重启失败");
//        }
//
//    }
//
//    //断开
//    @RequestMapping(value = "/breakShell",method = RequestMethod.GET)
//    public Result breakShell(String breakShell) throws Exception{
//       try {
//           //获取logger  日志
//           Logger logger =LoggerFactory.getLogger(getClass());
//
//           String shPath = breakShell;
//           Process ps = Runtime.getRuntime().exec(shPath);
//
//           //执行结果 并返回
//           int exitValue =  ps.waitFor();
//           // 如果 执行结果 不等于0  返回错误信息
//           if(0 != exitValue)
//               logger.info("call shell failed. error code is :" + exitValue);
//
//           //设置接收结果集
//           String result = null;
//
//           //获取shell返回流
//           BufferedInputStream in =new BufferedInputStream(ps.getInputStream());
//           //字符流转换字节流
//           BufferedReader br =new BufferedReader(new InputStreamReader(in));
//           //循环字节流 获得结果
//           String lineStr;
//           while ((lineStr = br.readLine()) != null){
//               result=lineStr;
//           }
//           //关流
//           br.close();
//           in.close();
//           //打印返回结果
//           logger.info("脚本返回结果如下："+result);
//           //  System.out.println("脚本返回结果如下："+result);
//
//           return new Result(true,"断开成功");
//       }catch (Exception e){
//           return new Result(true,"断开失败");
//       }
//       }
//
//
//    //批量重启指定的
//    @RequestMapping(value = "/aKeyToRestartShell",method = RequestMethod.GET)
//    public Result aKeyToRestartShell(Long[] ids) throws Exception {
//        try{
//        List<GTH> gthlist  =gthService.gthAll();
//        for (Long id: ids) {
//            for (GTH list: gthlist) {
//                if (id.equals(Long.parseLong(list.getId()))) {
//                    String shPath = list.getResetShell();
//                    Process ps = Runtime.getRuntime().exec(shPath);
//                    ps.waitFor();
//                }
//            }
//        }
//            return new Result(true,"重启成功");
//        }catch (Exception e){
//            return new Result(true,"重启失败");
//        }
//
//    }
//
//}




