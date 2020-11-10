package bean

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: bean
 * ClassName: CouponAlertInfo 
 *
 * @author 18729 created on date: 2020/11/10 9:43
 * @version 1.0
 * @since JDK 1.8
 */
case class CouponAlertInfo(mid:String,
                           uids:java.util.HashSet[String],
                           itemIds:java.util.HashSet[String],
                           events:java.util.List[String],
                           ts:Long)

