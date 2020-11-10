package bean

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: bean
 * ClassName: EventLog 
 *
 * @author 18729 created on date: 2020/11/10 9:10
 * @version 1.0
 * @since JDK 1.8
 */
case class EventLog(mid:String,
                    uid:String,
                    appid:String,
                    area:String,
                    os:String,
                    `type`:String,
                    evid:String,
                    pgid:String,
                    npgid:String,
                    itemid:String,
                    var logDate:String,
                    var logHour:String,
                    var ts:Long)

