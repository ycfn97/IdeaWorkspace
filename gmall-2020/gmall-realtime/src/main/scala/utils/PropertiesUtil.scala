package utils

import java.io.InputStreamReader
import java.util.Properties

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: utils
 * ClassName: PropertiesUtil
 *
 * @author 18729 created on date: 2020/11/4 13:36
 * @version 1.0
 * @since JDK 1.8
 */

object PropertiesUtil {
  def load(propertieName: String): Properties = {
    val prop = new Properties()
    prop.load(new InputStreamReader(Thread.currentThread().getContextClassLoader.getResourceAsStream(propertieName), "UTF-8"))
    prop
  }
}

