package bean

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: bean
 * ClassName: StartUpLog 
 *
 * @author 18729 created on date: 2020/11/4 14:47
 * @version 1.0
 * @since JDK 1.8
 */
case class StartUpLog(mid:String,
                      uid:String,
                      appid:String,
                      area:String,
                      os:String,
                      ch:String,
                      `type`:String,
                      vs:String,
                      var logDate:String,
                      var logHour:String,
                      var ts:Long)

