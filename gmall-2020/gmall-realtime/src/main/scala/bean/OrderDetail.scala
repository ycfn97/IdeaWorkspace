package bean

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: bean
 * ClassName: OrderDetail 
 *
 * @author 18729 created on date: 2020/11/10 19:55
 * @version 1.0
 * @since JDK 1.8
 */
case class OrderDetail(id:String,
                       order_id: String,
                       sku_name: String,
                       sku_id: String,
                       order_price: String,
                       img_url: String,
                       sku_num: String)

