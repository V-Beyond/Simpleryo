package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * @ClassNname：OrderListBean.java
 * @Describe 订单列表实体类
 * @author huanglei
 * @time 2018/4/13 10:31
 */
@HttpResponse(parser = JsonResponseParser.class)
public class OrderListBean extends  BaseResult{

    /**
     * data : [{"mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5fbfe75118310c41f3a657","creationTime":1533898343244,"id":"ff808081652374640165237743c60000","no":"1533898343264","courseId":"5b67e0865118317498214a59","courseName":"C语言基础教学","imageUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/c2c89b28652bef8bb342f4f396fb5c0c","discountAmt":1000,"unitPrice":1000,"quantity":2,"totalAmt":2000,"payAmt":1000,"payTime":null,"payType":"WECHATPAY","routeCode":"ANDROID","status":"NEW","items":null,"remark":null,"store":{"id":"5b5608c451183107612cf15f","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"system","creationTime":1532364996746,"followCount":null,"idNumber":"1423543544324","name":"微信小程序实战训练营","licenceUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/035085519526351021a99ab63fed9ae8","coverUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/7de613f0ae9b808531aa58aa45cd077f","linkman":"sober","phone":"13333333333","auditorId":"system","auditTime":1532365010642,"salesProdtCount":null,"salesAmount":null,"IdNumber":"1423543544324","accountName":null,"accountNo":null,"status":"AUDIT_OK","remark":null,"category":{"id":"5b5607d651183107612cf15b","name":"IT课程"},"hasRecommend":null,"address":{"id":null,"lat":null,"lng":null,"provice":null,"city":null,"district":null,"detail":"上海市闵行区恒南路","linkman":null,"phone":null,"gender":null,"isDefault":null},"authorities":null,"notifiers":null},"buyer":{"id":"5b5fbfe75118310c41f3a657","nickName":"WongLeoi","avatarUrl":"http://p.simpleryo.com/file/459e370c35a3778974d2d67f57ce41d6","name":"WongLeoi","phone":null,"address":null},"coach":{"id":"5b57d0e151183107612cf173","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1532481761846,"sequence":0,"no":"1532481761846","storeName":"微信小程序实战训练营","courseCount":5,"name":"Lucy","nickName":"Lisa","avatarUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/1787aa7bd09488b02e60865acfd5790e","studentCount":6,"point":0,"phone":"15221317052","chatNum":"","workLife":"3年-5年","intro":"瑜伽教练5年教龄经验","desc":"<p>从事于瑜伽教练5年经验<\/p>","skills":[{"value":"瑜伽"},{"value":"健身"}]},"course":{"id":"5b67e0865118317498214a59","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1533534342233,"name":"C语言基础教学","coverUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/c2c89b28652bef8bb342f4f396fb5c0c","tagId1":"5b5607d651183107612cf15b","tagId2":"5b666323511831226d406644","recommends":[{"value":"5b57dce351183107612cf181"}],"intro":"<p>这是一堂生动形象的C语言课程<\/p>","distance":null,"storeName":"微信小程序实战训练营","coachId":"5b57d0e151183107612cf173","enable":true,"collectCount":0,"classCount":5,"goodReviewRate":100,"hasCollect":false,"popular":null,"originPrice":4000,"price":1000,"grouprice":0,"durations":{"startDate":"2018-08-07","endDate":"2018-09-30","data":[{"startTime":"8:00","endTime":"16:00","week":"周一"},{"startTime":"08:00","endTime":"16:00","week":"周二"},{"startTime":"8:00","endTime":"16:00","week":"周三"},{"startTime":"8:00","endTime":"16:00","week":"周四"},{"startTime":"8:00","endTime":"16:00","week":"周五"},{"startTime":"8:00","endTime":"16:00","week":"周六"}]},"address":{"id":null,"lat":31.0972789353362,"lng":121.50768810750878,"provice":null,"city":null,"district":null,"detail":"中国上海市闵行区陈行公路2388号 邮政编码: 201112","linkman":null,"phone":null,"gender":null,"isDefault":null},"loc":[121.50768810750878,31.0972789353362],"coach":{"id":"5b57d0e151183107612cf173","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1532481761846,"sequence":0,"no":"1532481761846","storeName":"微信小程序实战训练营","courseCount":4,"name":"Lucy","nickName":"Lisa","avatarUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/1787aa7bd09488b02e60865acfd5790e","studentCount":6,"point":0,"phone":"15221317052","chatNum":"","workLife":"3年-5年","intro":"瑜伽教练5年教龄经验","desc":"<p>从事于瑜伽教练5年经验<\/p>","skills":[{"value":"瑜伽"},{"value":"健身"}]},"hasCardCoupon":true,"upperLimit":40,"type":"series","maxTime":null,"arranges":[{"id":"5b67e0865118317498214a6e","courseId":"5b67e0865118317498214a59","classDate":"2018-08-30","startTime":"2018-08-30 08:00:00","endTime":"2018-08-30 16:00:00","dateDetail":"2018-08-30 08:00-16:00"},{"id":"5b67e0865118317498214a6f","courseId":"5b67e0865118317498214a59","classDate":"2018-08-31","startTime":"2018-08-31 08:00:00","endTime":"2018-08-31 16:00:00","dateDetail":"2018-08-31 08:00-16:00"},{"id":"5b67e0865118317498214a70","courseId":"5b67e0865118317498214a59","classDate":"2018-09-01","startTime":"2018-09-01 08:00:00","endTime":"2018-09-01 16:00:00","dateDetail":"2018-09-01 08:00-16:00"},{"id":"5b67e0865118317498214a71","courseId":"5b67e0865118317498214a59","classDate":"2018-09-03","startTime":"2018-09-03 08:00:00","endTime":"2018-09-03 16:00:00","dateDetail":"2018-09-03 08:00-16:00"},{"id":"5b67e0865118317498214a72","courseId":"5b67e0865118317498214a59","classDate":"2018-09-04","startTime":"2018-09-04 08:00:00","endTime":"2018-09-04 16:00:00","dateDetail":"2018-09-04 08:00-16:00"},{"id":"5b67e0865118317498214a73","courseId":"5b67e0865118317498214a59","classDate":"2018-09-05","startTime":"2018-09-05 08:00:00","endTime":"2018-09-05 16:00:00","dateDetail":"2018-09-05 08:00-16:00"},{"id":"5b67e0865118317498214a74","courseId":"5b67e0865118317498214a59","classDate":"2018-09-06","startTime":"2018-09-06 08:00:00","endTime":"2018-09-06 16:00:00","dateDetail":"2018-09-06 08:00-16:00"},{"id":"5b67e0865118317498214a75","courseId":"5b67e0865118317498214a59","classDate":"2018-09-07","startTime":"2018-09-07 08:00:00","endTime":"2018-09-07 16:00:00","dateDetail":"2018-09-07 08:00-16:00"},{"id":"5b67e0865118317498214a76","courseId":"5b67e0865118317498214a59","classDate":"2018-09-08","startTime":"2018-09-08 08:00:00","endTime":"2018-09-08 16:00:00","dateDetail":"2018-09-08 08:00-16:00"},{"id":"5b67e0865118317498214a77","courseId":"5b67e0865118317498214a59","classDate":"2018-09-10","startTime":"2018-09-10 08:00:00","endTime":"2018-09-10 16:00:00","dateDetail":"2018-09-10 08:00-16:00"},{"id":"5b67e0865118317498214a78","courseId":"5b67e0865118317498214a59","classDate":"2018-09-11","startTime":"2018-09-11 08:00:00","endTime":"2018-09-11 16:00:00","dateDetail":"2018-09-11 08:00-16:00"},{"id":"5b67e0865118317498214a79","courseId":"5b67e0865118317498214a59","classDate":"2018-09-12","startTime":"2018-09-12 08:00:00","endTime":"2018-09-12 16:00:00","dateDetail":"2018-09-12 08:00-16:00"},{"id":"5b67e0865118317498214a7a","courseId":"5b67e0865118317498214a59","classDate":"2018-09-13","startTime":"2018-09-13 08:00:00","endTime":"2018-09-13 16:00:00","dateDetail":"2018-09-13 08:00-16:00"},{"id":"5b67e0865118317498214a7b","courseId":"5b67e0865118317498214a59","classDate":"2018-09-14","startTime":"2018-09-14 08:00:00","endTime":"2018-09-14 16:00:00","dateDetail":"2018-09-14 08:00-16:00"},{"id":"5b67e0865118317498214a7c","courseId":"5b67e0865118317498214a59","classDate":"2018-09-15","startTime":"2018-09-15 08:00:00","endTime":"2018-09-15 16:00:00","dateDetail":"2018-09-15 08:00-16:00"},{"id":"5b67e0865118317498214a7d","courseId":"5b67e0865118317498214a59","classDate":"2018-09-17","startTime":"2018-09-17 08:00:00","endTime":"2018-09-17 16:00:00","dateDetail":"2018-09-17 08:00-16:00"},{"id":"5b67e0865118317498214a7e","courseId":"5b67e0865118317498214a59","classDate":"2018-09-18","startTime":"2018-09-18 08:00:00","endTime":"2018-09-18 16:00:00","dateDetail":"2018-09-18 08:00-16:00"},{"id":"5b67e0865118317498214a7f","courseId":"5b67e0865118317498214a59","classDate":"2018-09-19","startTime":"2018-09-19 08:00:00","endTime":"2018-09-19 16:00:00","dateDetail":"2018-09-19 08:00-16:00"},{"id":"5b67e0865118317498214a80","courseId":"5b67e0865118317498214a59","classDate":"2018-09-20","startTime":"2018-09-20 08:00:00","endTime":"2018-09-20 16:00:00","dateDetail":"2018-09-20 08:00-16:00"},{"id":"5b67e0865118317498214a81","courseId":"5b67e0865118317498214a59","classDate":"2018-09-21","startTime":"2018-09-21 08:00:00","endTime":"2018-09-21 16:00:00","dateDetail":"2018-09-21 08:00-16:00"},{"id":"5b67e0865118317498214a82","courseId":"5b67e0865118317498214a59","classDate":"2018-09-22","startTime":"2018-09-22 08:00:00","endTime":"2018-09-22 16:00:00","dateDetail":"2018-09-22 08:00-16:00"},{"id":"5b67e0865118317498214a83","courseId":"5b67e0865118317498214a59","classDate":"2018-09-24","startTime":"2018-09-24 08:00:00","endTime":"2018-09-24 16:00:00","dateDetail":"2018-09-24 08:00-16:00"},{"id":"5b67e0865118317498214a84","courseId":"5b67e0865118317498214a59","classDate":"2018-09-25","startTime":"2018-09-25 08:00:00","endTime":"2018-09-25 16:00:00","dateDetail":"2018-09-25 08:00-16:00"},{"id":"5b67e0865118317498214a85","courseId":"5b67e0865118317498214a59","classDate":"2018-09-26","startTime":"2018-09-26 08:00:00","endTime":"2018-09-26 16:00:00","dateDetail":"2018-09-26 08:00-16:00"},{"id":"5b67e0865118317498214a86","courseId":"5b67e0865118317498214a59","classDate":"2018-09-27","startTime":"2018-09-27 08:00:00","endTime":"2018-09-27 16:00:00","dateDetail":"2018-09-27 08:00-16:00"},{"id":"5b67e0865118317498214a87","courseId":"5b67e0865118317498214a59","classDate":"2018-09-28","startTime":"2018-09-28 08:00:00","endTime":"2018-09-28 16:00:00","dateDetail":"2018-09-28 08:00-16:00"},{"id":"5b67e0865118317498214a88","courseId":"5b67e0865118317498214a59","classDate":"2018-09-29","startTime":"2018-09-29 08:00:00","endTime":"2018-09-29 16:00:00","dateDetail":"2018-09-29 08:00-16:00"}]},"userPhone":"15221316476","userName":"黄磊","userRemark":"优惠券","discountDetails":[],"arrangeFlag":true,"classTime":null},{"mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5fbfe75118310c41f3a657","creationTime":1533882042820,"id":"ff80808165226ef00165227e8a0e0000","no":"1533882042828","courseId":"5b67e0865118317498214a59","courseName":"C语言基础教学","imageUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/c2c89b28652bef8bb342f4f396fb5c0c","discountAmt":890,"unitPrice":1000,"quantity":1,"totalAmt":1000,"payAmt":110,"payTime":null,"payType":"WECHATPAY","routeCode":"ANDROID","status":"NEW","items":null,"remark":null,"store":{"id":"5b5608c451183107612cf15f","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"system","creationTime":1532364996746,"followCount":null,"idNumber":"1423543544324","name":"微信小程序实战训练营","licenceUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/035085519526351021a99ab63fed9ae8","coverUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/7de613f0ae9b808531aa58aa45cd077f","linkman":"sober","phone":"13333333333","auditorId":"system","auditTime":1532365010642,"salesProdtCount":null,"salesAmount":null,"IdNumber":"1423543544324","accountName":null,"accountNo":null,"status":"AUDIT_OK","remark":null,"category":{"id":"5b5607d651183107612cf15b","name":"IT课程"},"hasRecommend":null,"address":{"id":null,"lat":null,"lng":null,"provice":null,"city":null,"district":null,"detail":"上海市闵行区恒南路","linkman":null,"phone":null,"gender":null,"isDefault":null},"authorities":null,"notifiers":null},"buyer":{"id":"5b5fbfe75118310c41f3a657","nickName":"WongLeoi","avatarUrl":"http://p.simpleryo.com/file/459e370c35a3778974d2d67f57ce41d6","name":"WongLeoi","phone":null,"address":null},"coach":{"id":"5b57d0e151183107612cf173","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1532481761846,"sequence":0,"no":"1532481761846","storeName":"微信小程序实战训练营","courseCount":5,"name":"Lucy","nickName":"Lisa","avatarUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/1787aa7bd09488b02e60865acfd5790e","studentCount":6,"point":0,"phone":"15221317052","chatNum":"","workLife":"3年-5年","intro":"瑜伽教练5年教龄经验","desc":"<p>从事于瑜伽教练5年经验<\/p>","skills":[{"value":"瑜伽"},{"value":"健身"}]},"course":{"id":"5b67e0865118317498214a59","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1533534342233,"name":"C语言基础教学","coverUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/c2c89b28652bef8bb342f4f396fb5c0c","tagId1":"5b5607d651183107612cf15b","tagId2":"5b666323511831226d406644","recommends":[{"value":"5b57dce351183107612cf181"}],"intro":"<p>这是一堂生动形象的C语言课程<\/p>","distance":null,"storeName":"微信小程序实战训练营","coachId":"5b57d0e151183107612cf173","enable":true,"collectCount":0,"classCount":5,"goodReviewRate":100,"hasCollect":false,"popular":null,"originPrice":4000,"price":1000,"grouprice":0,"durations":{"startDate":"2018-08-07","endDate":"2018-09-30","data":[{"startTime":"8:00","endTime":"16:00","week":"周一"},{"startTime":"08:00","endTime":"16:00","week":"周二"},{"startTime":"8:00","endTime":"16:00","week":"周三"},{"startTime":"8:00","endTime":"16:00","week":"周四"},{"startTime":"8:00","endTime":"16:00","week":"周五"},{"startTime":"8:00","endTime":"16:00","week":"周六"}]},"address":{"id":null,"lat":31.0972789353362,"lng":121.50768810750878,"provice":null,"city":null,"district":null,"detail":"中国上海市闵行区陈行公路2388号 邮政编码: 201112","linkman":null,"phone":null,"gender":null,"isDefault":null},"loc":[121.50768810750878,31.0972789353362],"coach":{"id":"5b57d0e151183107612cf173","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1532481761846,"sequence":0,"no":"1532481761846","storeName":"微信小程序实战训练营","courseCount":4,"name":"Lucy","nickName":"Lisa","avatarUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/1787aa7bd09488b02e60865acfd5790e","studentCount":6,"point":0,"phone":"15221317052","chatNum":"","workLife":"3年-5年","intro":"瑜伽教练5年教龄经验","desc":"<p>从事于瑜伽教练5年经验<\/p>","skills":[{"value":"瑜伽"},{"value":"健身"}]},"hasCardCoupon":true,"upperLimit":40,"type":"series","maxTime":null,"arranges":[{"id":"5b67e0865118317498214a6e","courseId":"5b67e0865118317498214a59","classDate":"2018-08-30","startTime":"2018-08-30 08:00:00","endTime":"2018-08-30 16:00:00","dateDetail":"2018-08-30 08:00-16:00"},{"id":"5b67e0865118317498214a6f","courseId":"5b67e0865118317498214a59","classDate":"2018-08-31","startTime":"2018-08-31 08:00:00","endTime":"2018-08-31 16:00:00","dateDetail":"2018-08-31 08:00-16:00"},{"id":"5b67e0865118317498214a70","courseId":"5b67e0865118317498214a59","classDate":"2018-09-01","startTime":"2018-09-01 08:00:00","endTime":"2018-09-01 16:00:00","dateDetail":"2018-09-01 08:00-16:00"},{"id":"5b67e0865118317498214a71","courseId":"5b67e0865118317498214a59","classDate":"2018-09-03","startTime":"2018-09-03 08:00:00","endTime":"2018-09-03 16:00:00","dateDetail":"2018-09-03 08:00-16:00"},{"id":"5b67e0865118317498214a72","courseId":"5b67e0865118317498214a59","classDate":"2018-09-04","startTime":"2018-09-04 08:00:00","endTime":"2018-09-04 16:00:00","dateDetail":"2018-09-04 08:00-16:00"},{"id":"5b67e0865118317498214a73","courseId":"5b67e0865118317498214a59","classDate":"2018-09-05","startTime":"2018-09-05 08:00:00","endTime":"2018-09-05 16:00:00","dateDetail":"2018-09-05 08:00-16:00"},{"id":"5b67e0865118317498214a74","courseId":"5b67e0865118317498214a59","classDate":"2018-09-06","startTime":"2018-09-06 08:00:00","endTime":"2018-09-06 16:00:00","dateDetail":"2018-09-06 08:00-16:00"},{"id":"5b67e0865118317498214a75","courseId":"5b67e0865118317498214a59","classDate":"2018-09-07","startTime":"2018-09-07 08:00:00","endTime":"2018-09-07 16:00:00","dateDetail":"2018-09-07 08:00-16:00"},{"id":"5b67e0865118317498214a76","courseId":"5b67e0865118317498214a59","classDate":"2018-09-08","startTime":"2018-09-08 08:00:00","endTime":"2018-09-08 16:00:00","dateDetail":"2018-09-08 08:00-16:00"},{"id":"5b67e0865118317498214a77","courseId":"5b67e0865118317498214a59","classDate":"2018-09-10","startTime":"2018-09-10 08:00:00","endTime":"2018-09-10 16:00:00","dateDetail":"2018-09-10 08:00-16:00"},{"id":"5b67e0865118317498214a78","courseId":"5b67e0865118317498214a59","classDate":"2018-09-11","startTime":"2018-09-11 08:00:00","endTime":"2018-09-11 16:00:00","dateDetail":"2018-09-11 08:00-16:00"},{"id":"5b67e0865118317498214a79","courseId":"5b67e0865118317498214a59","classDate":"2018-09-12","startTime":"2018-09-12 08:00:00","endTime":"2018-09-12 16:00:00","dateDetail":"2018-09-12 08:00-16:00"},{"id":"5b67e0865118317498214a7a","courseId":"5b67e0865118317498214a59","classDate":"2018-09-13","startTime":"2018-09-13 08:00:00","endTime":"2018-09-13 16:00:00","dateDetail":"2018-09-13 08:00-16:00"},{"id":"5b67e0865118317498214a7b","courseId":"5b67e0865118317498214a59","classDate":"2018-09-14","startTime":"2018-09-14 08:00:00","endTime":"2018-09-14 16:00:00","dateDetail":"2018-09-14 08:00-16:00"},{"id":"5b67e0865118317498214a7c","courseId":"5b67e0865118317498214a59","classDate":"2018-09-15","startTime":"2018-09-15 08:00:00","endTime":"2018-09-15 16:00:00","dateDetail":"2018-09-15 08:00-16:00"},{"id":"5b67e0865118317498214a7d","courseId":"5b67e0865118317498214a59","classDate":"2018-09-17","startTime":"2018-09-17 08:00:00","endTime":"2018-09-17 16:00:00","dateDetail":"2018-09-17 08:00-16:00"},{"id":"5b67e0865118317498214a7e","courseId":"5b67e0865118317498214a59","classDate":"2018-09-18","startTime":"2018-09-18 08:00:00","endTime":"2018-09-18 16:00:00","dateDetail":"2018-09-18 08:00-16:00"},{"id":"5b67e0865118317498214a7f","courseId":"5b67e0865118317498214a59","classDate":"2018-09-19","startTime":"2018-09-19 08:00:00","endTime":"2018-09-19 16:00:00","dateDetail":"2018-09-19 08:00-16:00"},{"id":"5b67e0865118317498214a80","courseId":"5b67e0865118317498214a59","classDate":"2018-09-20","startTime":"2018-09-20 08:00:00","endTime":"2018-09-20 16:00:00","dateDetail":"2018-09-20 08:00-16:00"},{"id":"5b67e0865118317498214a81","courseId":"5b67e0865118317498214a59","classDate":"2018-09-21","startTime":"2018-09-21 08:00:00","endTime":"2018-09-21 16:00:00","dateDetail":"2018-09-21 08:00-16:00"},{"id":"5b67e0865118317498214a82","courseId":"5b67e0865118317498214a59","classDate":"2018-09-22","startTime":"2018-09-22 08:00:00","endTime":"2018-09-22 16:00:00","dateDetail":"2018-09-22 08:00-16:00"},{"id":"5b67e0865118317498214a83","courseId":"5b67e0865118317498214a59","classDate":"2018-09-24","startTime":"2018-09-24 08:00:00","endTime":"2018-09-24 16:00:00","dateDetail":"2018-09-24 08:00-16:00"},{"id":"5b67e0865118317498214a84","courseId":"5b67e0865118317498214a59","classDate":"2018-09-25","startTime":"2018-09-25 08:00:00","endTime":"2018-09-25 16:00:00","dateDetail":"2018-09-25 08:00-16:00"},{"id":"5b67e0865118317498214a85","courseId":"5b67e0865118317498214a59","classDate":"2018-09-26","startTime":"2018-09-26 08:00:00","endTime":"2018-09-26 16:00:00","dateDetail":"2018-09-26 08:00-16:00"},{"id":"5b67e0865118317498214a86","courseId":"5b67e0865118317498214a59","classDate":"2018-09-27","startTime":"2018-09-27 08:00:00","endTime":"2018-09-27 16:00:00","dateDetail":"2018-09-27 08:00-16:00"},{"id":"5b67e0865118317498214a87","courseId":"5b67e0865118317498214a59","classDate":"2018-09-28","startTime":"2018-09-28 08:00:00","endTime":"2018-09-28 16:00:00","dateDetail":"2018-09-28 08:00-16:00"},{"id":"5b67e0865118317498214a88","courseId":"5b67e0865118317498214a59","classDate":"2018-09-29","startTime":"2018-09-29 08:00:00","endTime":"2018-09-29 16:00:00","dateDetail":"2018-09-29 08:00-16:00"}]},"userPhone":"15221316476","userName":"黄磊","userRemark":"优惠券下单","discountDetails":[],"arrangeFlag":true,"classTime":null},{"mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5fbfe75118310c41f3a657","creationTime":1533805285165,"id":"ff808081651d9fc201651deb4f580000","no":"1533805285178","courseId":"5b67e0865118317498214a59","courseName":"C语言基础教学","imageUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/c2c89b28652bef8bb342f4f396fb5c0c","discountAmt":null,"unitPrice":1000,"quantity":1,"totalAmt":1000,"payAmt":890,"payTime":null,"payType":"WECHATPAY","routeCode":"ANDROID","status":"NEW","items":null,"remark":null,"store":{"id":"5b5608c451183107612cf15f","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"system","creationTime":1532364996746,"followCount":null,"idNumber":"1423543544324","name":"微信小程序实战训练营","licenceUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/035085519526351021a99ab63fed9ae8","coverUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/7de613f0ae9b808531aa58aa45cd077f","linkman":"sober","phone":"13333333333","auditorId":"system","auditTime":1532365010642,"salesProdtCount":null,"salesAmount":null,"IdNumber":"1423543544324","accountName":null,"accountNo":null,"status":"AUDIT_OK","remark":null,"category":{"id":"5b5607d651183107612cf15b","name":"IT课程"},"hasRecommend":null,"address":{"id":null,"lat":null,"lng":null,"provice":null,"city":null,"district":null,"detail":"上海市闵行区恒南路","linkman":null,"phone":null,"gender":null,"isDefault":null},"authorities":null,"notifiers":null},"buyer":{"id":"5b5fbfe75118310c41f3a657","nickName":"WongLeoi","avatarUrl":"http://p.simpleryo.com/file/459e370c35a3778974d2d67f57ce41d6","name":"WongLeoi","phone":null,"address":null},"coach":{"id":"5b57d0e151183107612cf173","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1532481761846,"sequence":0,"no":"1532481761846","storeName":"微信小程序实战训练营","courseCount":5,"name":"Lucy","nickName":"Lisa","avatarUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/1787aa7bd09488b02e60865acfd5790e","studentCount":6,"point":0,"phone":"15221317052","chatNum":"","workLife":"3年-5年","intro":"瑜伽教练5年教龄经验","desc":"<p>从事于瑜伽教练5年经验<\/p>","skills":[{"value":"瑜伽"},{"value":"健身"}]},"course":{"id":"5b67e0865118317498214a59","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1533534342233,"name":"C语言基础教学","coverUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/c2c89b28652bef8bb342f4f396fb5c0c","tagId1":"5b5607d651183107612cf15b","tagId2":"5b666323511831226d406644","recommends":[{"value":"5b57dce351183107612cf181"}],"intro":"<p>这是一堂生动形象的C语言课程<\/p>","distance":null,"storeName":"微信小程序实战训练营","coachId":"5b57d0e151183107612cf173","enable":true,"collectCount":0,"classCount":5,"goodReviewRate":100,"hasCollect":false,"popular":null,"originPrice":4000,"price":1000,"grouprice":0,"durations":{"startDate":"2018-08-07","endDate":"2018-09-30","data":[{"startTime":"8:00","endTime":"16:00","week":"周一"},{"startTime":"08:00","endTime":"16:00","week":"周二"},{"startTime":"8:00","endTime":"16:00","week":"周三"},{"startTime":"8:00","endTime":"16:00","week":"周四"},{"startTime":"8:00","endTime":"16:00","week":"周五"},{"startTime":"8:00","endTime":"16:00","week":"周六"}]},"address":{"id":null,"lat":31.0972789353362,"lng":121.50768810750878,"provice":null,"city":null,"district":null,"detail":"中国上海市闵行区陈行公路2388号 邮政编码: 201112","linkman":null,"phone":null,"gender":null,"isDefault":null},"loc":[121.50768810750878,31.0972789353362],"coach":{"id":"5b57d0e151183107612cf173","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1532481761846,"sequence":0,"no":"1532481761846","storeName":"微信小程序实战训练营","courseCount":4,"name":"Lucy","nickName":"Lisa","avatarUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/1787aa7bd09488b02e60865acfd5790e","studentCount":6,"point":0,"phone":"15221317052","chatNum":"","workLife":"3年-5年","intro":"瑜伽教练5年教龄经验","desc":"<p>从事于瑜伽教练5年经验<\/p>","skills":[{"value":"瑜伽"},{"value":"健身"}]},"hasCardCoupon":true,"upperLimit":40,"type":"series","maxTime":null,"arranges":[{"id":"5b67e0865118317498214a6e","courseId":"5b67e0865118317498214a59","classDate":"2018-08-30","startTime":"2018-08-30 08:00:00","endTime":"2018-08-30 16:00:00","dateDetail":"2018-08-30 08:00-16:00"},{"id":"5b67e0865118317498214a6f","courseId":"5b67e0865118317498214a59","classDate":"2018-08-31","startTime":"2018-08-31 08:00:00","endTime":"2018-08-31 16:00:00","dateDetail":"2018-08-31 08:00-16:00"},{"id":"5b67e0865118317498214a70","courseId":"5b67e0865118317498214a59","classDate":"2018-09-01","startTime":"2018-09-01 08:00:00","endTime":"2018-09-01 16:00:00","dateDetail":"2018-09-01 08:00-16:00"},{"id":"5b67e0865118317498214a71","courseId":"5b67e0865118317498214a59","classDate":"2018-09-03","startTime":"2018-09-03 08:00:00","endTime":"2018-09-03 16:00:00","dateDetail":"2018-09-03 08:00-16:00"},{"id":"5b67e0865118317498214a72","courseId":"5b67e0865118317498214a59","classDate":"2018-09-04","startTime":"2018-09-04 08:00:00","endTime":"2018-09-04 16:00:00","dateDetail":"2018-09-04 08:00-16:00"},{"id":"5b67e0865118317498214a73","courseId":"5b67e0865118317498214a59","classDate":"2018-09-05","startTime":"2018-09-05 08:00:00","endTime":"2018-09-05 16:00:00","dateDetail":"2018-09-05 08:00-16:00"},{"id":"5b67e0865118317498214a74","courseId":"5b67e0865118317498214a59","classDate":"2018-09-06","startTime":"2018-09-06 08:00:00","endTime":"2018-09-06 16:00:00","dateDetail":"2018-09-06 08:00-16:00"},{"id":"5b67e0865118317498214a75","courseId":"5b67e0865118317498214a59","classDate":"2018-09-07","startTime":"2018-09-07 08:00:00","endTime":"2018-09-07 16:00:00","dateDetail":"2018-09-07 08:00-16:00"},{"id":"5b67e0865118317498214a76","courseId":"5b67e0865118317498214a59","classDate":"2018-09-08","startTime":"2018-09-08 08:00:00","endTime":"2018-09-08 16:00:00","dateDetail":"2018-09-08 08:00-16:00"},{"id":"5b67e0865118317498214a77","courseId":"5b67e0865118317498214a59","classDate":"2018-09-10","startTime":"2018-09-10 08:00:00","endTime":"2018-09-10 16:00:00","dateDetail":"2018-09-10 08:00-16:00"},{"id":"5b67e0865118317498214a78","courseId":"5b67e0865118317498214a59","classDate":"2018-09-11","startTime":"2018-09-11 08:00:00","endTime":"2018-09-11 16:00:00","dateDetail":"2018-09-11 08:00-16:00"},{"id":"5b67e0865118317498214a79","courseId":"5b67e0865118317498214a59","classDate":"2018-09-12","startTime":"2018-09-12 08:00:00","endTime":"2018-09-12 16:00:00","dateDetail":"2018-09-12 08:00-16:00"},{"id":"5b67e0865118317498214a7a","courseId":"5b67e0865118317498214a59","classDate":"2018-09-13","startTime":"2018-09-13 08:00:00","endTime":"2018-09-13 16:00:00","dateDetail":"2018-09-13 08:00-16:00"},{"id":"5b67e0865118317498214a7b","courseId":"5b67e0865118317498214a59","classDate":"2018-09-14","startTime":"2018-09-14 08:00:00","endTime":"2018-09-14 16:00:00","dateDetail":"2018-09-14 08:00-16:00"},{"id":"5b67e0865118317498214a7c","courseId":"5b67e0865118317498214a59","classDate":"2018-09-15","startTime":"2018-09-15 08:00:00","endTime":"2018-09-15 16:00:00","dateDetail":"2018-09-15 08:00-16:00"},{"id":"5b67e0865118317498214a7d","courseId":"5b67e0865118317498214a59","classDate":"2018-09-17","startTime":"2018-09-17 08:00:00","endTime":"2018-09-17 16:00:00","dateDetail":"2018-09-17 08:00-16:00"},{"id":"5b67e0865118317498214a7e","courseId":"5b67e0865118317498214a59","classDate":"2018-09-18","startTime":"2018-09-18 08:00:00","endTime":"2018-09-18 16:00:00","dateDetail":"2018-09-18 08:00-16:00"},{"id":"5b67e0865118317498214a7f","courseId":"5b67e0865118317498214a59","classDate":"2018-09-19","startTime":"2018-09-19 08:00:00","endTime":"2018-09-19 16:00:00","dateDetail":"2018-09-19 08:00-16:00"},{"id":"5b67e0865118317498214a80","courseId":"5b67e0865118317498214a59","classDate":"2018-09-20","startTime":"2018-09-20 08:00:00","endTime":"2018-09-20 16:00:00","dateDetail":"2018-09-20 08:00-16:00"},{"id":"5b67e0865118317498214a81","courseId":"5b67e0865118317498214a59","classDate":"2018-09-21","startTime":"2018-09-21 08:00:00","endTime":"2018-09-21 16:00:00","dateDetail":"2018-09-21 08:00-16:00"},{"id":"5b67e0865118317498214a82","courseId":"5b67e0865118317498214a59","classDate":"2018-09-22","startTime":"2018-09-22 08:00:00","endTime":"2018-09-22 16:00:00","dateDetail":"2018-09-22 08:00-16:00"},{"id":"5b67e0865118317498214a83","courseId":"5b67e0865118317498214a59","classDate":"2018-09-24","startTime":"2018-09-24 08:00:00","endTime":"2018-09-24 16:00:00","dateDetail":"2018-09-24 08:00-16:00"},{"id":"5b67e0865118317498214a84","courseId":"5b67e0865118317498214a59","classDate":"2018-09-25","startTime":"2018-09-25 08:00:00","endTime":"2018-09-25 16:00:00","dateDetail":"2018-09-25 08:00-16:00"},{"id":"5b67e0865118317498214a85","courseId":"5b67e0865118317498214a59","classDate":"2018-09-26","startTime":"2018-09-26 08:00:00","endTime":"2018-09-26 16:00:00","dateDetail":"2018-09-26 08:00-16:00"},{"id":"5b67e0865118317498214a86","courseId":"5b67e0865118317498214a59","classDate":"2018-09-27","startTime":"2018-09-27 08:00:00","endTime":"2018-09-27 16:00:00","dateDetail":"2018-09-27 08:00-16:00"},{"id":"5b67e0865118317498214a87","courseId":"5b67e0865118317498214a59","classDate":"2018-09-28","startTime":"2018-09-28 08:00:00","endTime":"2018-09-28 16:00:00","dateDetail":"2018-09-28 08:00-16:00"},{"id":"5b67e0865118317498214a88","courseId":"5b67e0865118317498214a59","classDate":"2018-09-29","startTime":"2018-09-29 08:00:00","endTime":"2018-09-29 16:00:00","dateDetail":"2018-09-29 08:00-16:00"}]},"userPhone":"15221316476","userName":"黄磊","userRemark":"测试优惠券下单","discountDetails":[],"arrangeFlag":true,"classTime":null}]
     * count : 3
     */

    private int count;
    private List<DataBeanX> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * mid : 01
         * storeId : 5b5608c451183107612cf15f
         * creatorId : 5b5fbfe75118310c41f3a657
         * creationTime : 1533898343244
         * id : ff808081652374640165237743c60000
         * no : 1533898343264
         * courseId : 5b67e0865118317498214a59
         * courseName : C语言基础教学
         * imageUrl : https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/c2c89b28652bef8bb342f4f396fb5c0c
         * discountAmt : 1000
         * unitPrice : 1000
         * quantity : 2
         * totalAmt : 2000
         * payAmt : 1000
         * payTime : null
         * payType : WECHATPAY
         * routeCode : ANDROID
         * status : NEW
         * items : null
         * remark : null
         * store : {"id":"5b5608c451183107612cf15f","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"system","creationTime":1532364996746,"followCount":null,"idNumber":"1423543544324","name":"微信小程序实战训练营","licenceUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/035085519526351021a99ab63fed9ae8","coverUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/7de613f0ae9b808531aa58aa45cd077f","linkman":"sober","phone":"13333333333","auditorId":"system","auditTime":1532365010642,"salesProdtCount":null,"salesAmount":null,"IdNumber":"1423543544324","accountName":null,"accountNo":null,"status":"AUDIT_OK","remark":null,"category":{"id":"5b5607d651183107612cf15b","name":"IT课程"},"hasRecommend":null,"address":{"id":null,"lat":null,"lng":null,"provice":null,"city":null,"district":null,"detail":"上海市闵行区恒南路","linkman":null,"phone":null,"gender":null,"isDefault":null},"authorities":null,"notifiers":null}
         * buyer : {"id":"5b5fbfe75118310c41f3a657","nickName":"WongLeoi","avatarUrl":"http://p.simpleryo.com/file/459e370c35a3778974d2d67f57ce41d6","name":"WongLeoi","phone":null,"address":null}
         * coach : {"id":"5b57d0e151183107612cf173","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1532481761846,"sequence":0,"no":"1532481761846","storeName":"微信小程序实战训练营","courseCount":5,"name":"Lucy","nickName":"Lisa","avatarUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/1787aa7bd09488b02e60865acfd5790e","studentCount":6,"point":0,"phone":"15221317052","chatNum":"","workLife":"3年-5年","intro":"瑜伽教练5年教龄经验","desc":"<p>从事于瑜伽教练5年经验<\/p>","skills":[{"value":"瑜伽"},{"value":"健身"}]}
         * course : {"id":"5b67e0865118317498214a59","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1533534342233,"name":"C语言基础教学","coverUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/c2c89b28652bef8bb342f4f396fb5c0c","tagId1":"5b5607d651183107612cf15b","tagId2":"5b666323511831226d406644","recommends":[{"value":"5b57dce351183107612cf181"}],"intro":"<p>这是一堂生动形象的C语言课程<\/p>","distance":null,"storeName":"微信小程序实战训练营","coachId":"5b57d0e151183107612cf173","enable":true,"collectCount":0,"classCount":5,"goodReviewRate":100,"hasCollect":false,"popular":null,"originPrice":4000,"price":1000,"grouprice":0,"durations":{"startDate":"2018-08-07","endDate":"2018-09-30","data":[{"startTime":"8:00","endTime":"16:00","week":"周一"},{"startTime":"08:00","endTime":"16:00","week":"周二"},{"startTime":"8:00","endTime":"16:00","week":"周三"},{"startTime":"8:00","endTime":"16:00","week":"周四"},{"startTime":"8:00","endTime":"16:00","week":"周五"},{"startTime":"8:00","endTime":"16:00","week":"周六"}]},"address":{"id":null,"lat":31.0972789353362,"lng":121.50768810750878,"provice":null,"city":null,"district":null,"detail":"中国上海市闵行区陈行公路2388号 邮政编码: 201112","linkman":null,"phone":null,"gender":null,"isDefault":null},"loc":[121.50768810750878,31.0972789353362],"coach":{"id":"5b57d0e151183107612cf173","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1532481761846,"sequence":0,"no":"1532481761846","storeName":"微信小程序实战训练营","courseCount":4,"name":"Lucy","nickName":"Lisa","avatarUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/1787aa7bd09488b02e60865acfd5790e","studentCount":6,"point":0,"phone":"15221317052","chatNum":"","workLife":"3年-5年","intro":"瑜伽教练5年教龄经验","desc":"<p>从事于瑜伽教练5年经验<\/p>","skills":[{"value":"瑜伽"},{"value":"健身"}]},"hasCardCoupon":true,"upperLimit":40,"type":"series","maxTime":null,"arranges":[{"id":"5b67e0865118317498214a6e","courseId":"5b67e0865118317498214a59","classDate":"2018-08-30","startTime":"2018-08-30 08:00:00","endTime":"2018-08-30 16:00:00","dateDetail":"2018-08-30 08:00-16:00"},{"id":"5b67e0865118317498214a6f","courseId":"5b67e0865118317498214a59","classDate":"2018-08-31","startTime":"2018-08-31 08:00:00","endTime":"2018-08-31 16:00:00","dateDetail":"2018-08-31 08:00-16:00"},{"id":"5b67e0865118317498214a70","courseId":"5b67e0865118317498214a59","classDate":"2018-09-01","startTime":"2018-09-01 08:00:00","endTime":"2018-09-01 16:00:00","dateDetail":"2018-09-01 08:00-16:00"},{"id":"5b67e0865118317498214a71","courseId":"5b67e0865118317498214a59","classDate":"2018-09-03","startTime":"2018-09-03 08:00:00","endTime":"2018-09-03 16:00:00","dateDetail":"2018-09-03 08:00-16:00"},{"id":"5b67e0865118317498214a72","courseId":"5b67e0865118317498214a59","classDate":"2018-09-04","startTime":"2018-09-04 08:00:00","endTime":"2018-09-04 16:00:00","dateDetail":"2018-09-04 08:00-16:00"},{"id":"5b67e0865118317498214a73","courseId":"5b67e0865118317498214a59","classDate":"2018-09-05","startTime":"2018-09-05 08:00:00","endTime":"2018-09-05 16:00:00","dateDetail":"2018-09-05 08:00-16:00"},{"id":"5b67e0865118317498214a74","courseId":"5b67e0865118317498214a59","classDate":"2018-09-06","startTime":"2018-09-06 08:00:00","endTime":"2018-09-06 16:00:00","dateDetail":"2018-09-06 08:00-16:00"},{"id":"5b67e0865118317498214a75","courseId":"5b67e0865118317498214a59","classDate":"2018-09-07","startTime":"2018-09-07 08:00:00","endTime":"2018-09-07 16:00:00","dateDetail":"2018-09-07 08:00-16:00"},{"id":"5b67e0865118317498214a76","courseId":"5b67e0865118317498214a59","classDate":"2018-09-08","startTime":"2018-09-08 08:00:00","endTime":"2018-09-08 16:00:00","dateDetail":"2018-09-08 08:00-16:00"},{"id":"5b67e0865118317498214a77","courseId":"5b67e0865118317498214a59","classDate":"2018-09-10","startTime":"2018-09-10 08:00:00","endTime":"2018-09-10 16:00:00","dateDetail":"2018-09-10 08:00-16:00"},{"id":"5b67e0865118317498214a78","courseId":"5b67e0865118317498214a59","classDate":"2018-09-11","startTime":"2018-09-11 08:00:00","endTime":"2018-09-11 16:00:00","dateDetail":"2018-09-11 08:00-16:00"},{"id":"5b67e0865118317498214a79","courseId":"5b67e0865118317498214a59","classDate":"2018-09-12","startTime":"2018-09-12 08:00:00","endTime":"2018-09-12 16:00:00","dateDetail":"2018-09-12 08:00-16:00"},{"id":"5b67e0865118317498214a7a","courseId":"5b67e0865118317498214a59","classDate":"2018-09-13","startTime":"2018-09-13 08:00:00","endTime":"2018-09-13 16:00:00","dateDetail":"2018-09-13 08:00-16:00"},{"id":"5b67e0865118317498214a7b","courseId":"5b67e0865118317498214a59","classDate":"2018-09-14","startTime":"2018-09-14 08:00:00","endTime":"2018-09-14 16:00:00","dateDetail":"2018-09-14 08:00-16:00"},{"id":"5b67e0865118317498214a7c","courseId":"5b67e0865118317498214a59","classDate":"2018-09-15","startTime":"2018-09-15 08:00:00","endTime":"2018-09-15 16:00:00","dateDetail":"2018-09-15 08:00-16:00"},{"id":"5b67e0865118317498214a7d","courseId":"5b67e0865118317498214a59","classDate":"2018-09-17","startTime":"2018-09-17 08:00:00","endTime":"2018-09-17 16:00:00","dateDetail":"2018-09-17 08:00-16:00"},{"id":"5b67e0865118317498214a7e","courseId":"5b67e0865118317498214a59","classDate":"2018-09-18","startTime":"2018-09-18 08:00:00","endTime":"2018-09-18 16:00:00","dateDetail":"2018-09-18 08:00-16:00"},{"id":"5b67e0865118317498214a7f","courseId":"5b67e0865118317498214a59","classDate":"2018-09-19","startTime":"2018-09-19 08:00:00","endTime":"2018-09-19 16:00:00","dateDetail":"2018-09-19 08:00-16:00"},{"id":"5b67e0865118317498214a80","courseId":"5b67e0865118317498214a59","classDate":"2018-09-20","startTime":"2018-09-20 08:00:00","endTime":"2018-09-20 16:00:00","dateDetail":"2018-09-20 08:00-16:00"},{"id":"5b67e0865118317498214a81","courseId":"5b67e0865118317498214a59","classDate":"2018-09-21","startTime":"2018-09-21 08:00:00","endTime":"2018-09-21 16:00:00","dateDetail":"2018-09-21 08:00-16:00"},{"id":"5b67e0865118317498214a82","courseId":"5b67e0865118317498214a59","classDate":"2018-09-22","startTime":"2018-09-22 08:00:00","endTime":"2018-09-22 16:00:00","dateDetail":"2018-09-22 08:00-16:00"},{"id":"5b67e0865118317498214a83","courseId":"5b67e0865118317498214a59","classDate":"2018-09-24","startTime":"2018-09-24 08:00:00","endTime":"2018-09-24 16:00:00","dateDetail":"2018-09-24 08:00-16:00"},{"id":"5b67e0865118317498214a84","courseId":"5b67e0865118317498214a59","classDate":"2018-09-25","startTime":"2018-09-25 08:00:00","endTime":"2018-09-25 16:00:00","dateDetail":"2018-09-25 08:00-16:00"},{"id":"5b67e0865118317498214a85","courseId":"5b67e0865118317498214a59","classDate":"2018-09-26","startTime":"2018-09-26 08:00:00","endTime":"2018-09-26 16:00:00","dateDetail":"2018-09-26 08:00-16:00"},{"id":"5b67e0865118317498214a86","courseId":"5b67e0865118317498214a59","classDate":"2018-09-27","startTime":"2018-09-27 08:00:00","endTime":"2018-09-27 16:00:00","dateDetail":"2018-09-27 08:00-16:00"},{"id":"5b67e0865118317498214a87","courseId":"5b67e0865118317498214a59","classDate":"2018-09-28","startTime":"2018-09-28 08:00:00","endTime":"2018-09-28 16:00:00","dateDetail":"2018-09-28 08:00-16:00"},{"id":"5b67e0865118317498214a88","courseId":"5b67e0865118317498214a59","classDate":"2018-09-29","startTime":"2018-09-29 08:00:00","endTime":"2018-09-29 16:00:00","dateDetail":"2018-09-29 08:00-16:00"}]}
         * userPhone : 15221316476
         * userName : 黄磊
         * userRemark : 优惠券
         * discountDetails : []
         * arrangeFlag : true
         * classTime : null
         */

        private String mid;
        private String storeId;
        private String creatorId;
        private String creationTime;
        private String id;
        private String no;
        private String courseId;
        private String courseName;
        private String imageUrl;
        private int discountAmt;
        private int unitPrice;
        private int quantity;
        private int totalAmt;
        private int payAmt;
        private String payTime;
        private String payType;
        private String routeCode;
        private String status;
        private String items;
        private String remark;
        private StoreBean store;
        private BuyerBean buyer;
        private CoachBean coach;
        private CourseBean course;
        private String userPhone;
        private String userName;
        private String userRemark;
        private boolean arrangeFlag;
        private String classTime;
        private List<?> discountDetails;

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(String creatorId) {
            this.creatorId = creatorId;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getDiscountAmt() {
            return discountAmt;
        }

        public void setDiscountAmt(int discountAmt) {
            this.discountAmt = discountAmt;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getTotalAmt() {
            return totalAmt;
        }

        public void setTotalAmt(int totalAmt) {
            this.totalAmt = totalAmt;
        }

        public int getPayAmt() {
            return payAmt;
        }

        public void setPayAmt(int payAmt) {
            this.payAmt = payAmt;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getRouteCode() {
            return routeCode;
        }

        public void setRouteCode(String routeCode) {
            this.routeCode = routeCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getItems() {
            return items;
        }

        public void setItems(String items) {
            this.items = items;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public BuyerBean getBuyer() {
            return buyer;
        }

        public void setBuyer(BuyerBean buyer) {
            this.buyer = buyer;
        }

        public CoachBean getCoach() {
            return coach;
        }

        public void setCoach(CoachBean coach) {
            this.coach = coach;
        }

        public CourseBean getCourse() {
            return course;
        }

        public void setCourse(CourseBean course) {
            this.course = course;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserRemark() {
            return userRemark;
        }

        public void setUserRemark(String userRemark) {
            this.userRemark = userRemark;
        }

        public boolean isArrangeFlag() {
            return arrangeFlag;
        }

        public void setArrangeFlag(boolean arrangeFlag) {
            this.arrangeFlag = arrangeFlag;
        }

        public String getClassTime() {
            return classTime;
        }

        public void setClassTime(String classTime) {
            this.classTime = classTime;
        }

        public List<?> getDiscountDetails() {
            return discountDetails;
        }

        public void setDiscountDetails(List<?> discountDetails) {
            this.discountDetails = discountDetails;
        }

        public static class StoreBean {
            /**
             * id : 5b5608c451183107612cf15f
             * mid : 01
             * storeId : 5b5608c451183107612cf15f
             * creatorId : system
             * creationTime : 1532364996746
             * followCount : null
             * idNumber : 1423543544324
             * name : 微信小程序实战训练营
             * licenceUrl : https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/035085519526351021a99ab63fed9ae8
             * coverUrl : https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/7de613f0ae9b808531aa58aa45cd077f
             * linkman : sober
             * phone : 13333333333
             * auditorId : system
             * auditTime : 1532365010642
             * salesProdtCount : null
             * salesAmount : null
             * IdNumber : 1423543544324
             * accountName : null
             * accountNo : null
             * status : AUDIT_OK
             * remark : null
             * category : {"id":"5b5607d651183107612cf15b","name":"IT课程"}
             * hasRecommend : null
             * address : {"id":null,"lat":null,"lng":null,"provice":null,"city":null,"district":null,"detail":"上海市闵行区恒南路","linkman":null,"phone":null,"gender":null,"isDefault":null}
             * authorities : null
             * notifiers : null
             */

            private String id;
            private String mid;
            private String storeId;
            private String creatorId;
            private long creationTime;
            private String followCount;
            private String name;
            private String licenceUrl;
            private String coverUrl;
            private String linkman;
            private String phone;
            private String auditorId;
            private long auditTime;
            private String salesProdtCount;
            private String salesAmount;
            private String IdNumber;
            private String accountName;
            private String accountNo;
            private String status;
            private String remark;
            private CategoryBean category;
            private String hasRecommend;
            private AddressBean address;
            private String authorities;
            private String notifiers;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getCreatorId() {
                return creatorId;
            }

            public void setCreatorId(String creatorId) {
                this.creatorId = creatorId;
            }

            public long getCreationTime() {
                return creationTime;
            }

            public void setCreationTime(long creationTime) {
                this.creationTime = creationTime;
            }

            public String getFollowCount() {
                return followCount;
            }

            public void setFollowCount(String followCount) {
                this.followCount = followCount;
            }


            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLicenceUrl() {
                return licenceUrl;
            }

            public void setLicenceUrl(String licenceUrl) {
                this.licenceUrl = licenceUrl;
            }

            public String getCoverUrl() {
                return coverUrl;
            }

            public void setCoverUrl(String coverUrl) {
                this.coverUrl = coverUrl;
            }

            public String getLinkman() {
                return linkman;
            }

            public void setLinkman(String linkman) {
                this.linkman = linkman;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAuditorId() {
                return auditorId;
            }

            public void setAuditorId(String auditorId) {
                this.auditorId = auditorId;
            }

            public long getAuditTime() {
                return auditTime;
            }

            public void setAuditTime(long auditTime) {
                this.auditTime = auditTime;
            }

            public String getSalesProdtCount() {
                return salesProdtCount;
            }

            public void setSalesProdtCount(String salesProdtCount) {
                this.salesProdtCount = salesProdtCount;
            }

            public String getSalesAmount() {
                return salesAmount;
            }

            public void setSalesAmount(String salesAmount) {
                this.salesAmount = salesAmount;
            }

            public String getAccountName() {
                return accountName;
            }

            public void setAccountName(String accountName) {
                this.accountName = accountName;
            }

            public String getAccountNo() {
                return accountNo;
            }

            public void setAccountNo(String accountNo) {
                this.accountNo = accountNo;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public CategoryBean getCategory() {
                return category;
            }

            public void setCategory(CategoryBean category) {
                this.category = category;
            }

            public String getHasRecommend() {
                return hasRecommend;
            }

            public void setHasRecommend(String hasRecommend) {
                this.hasRecommend = hasRecommend;
            }

            public AddressBean getAddress() {
                return address;
            }

            public void setAddress(AddressBean address) {
                this.address = address;
            }

            public String getAuthorities() {
                return authorities;
            }

            public void setAuthorities(String authorities) {
                this.authorities = authorities;
            }

            public String getNotifiers() {
                return notifiers;
            }

            public void setNotifiers(String notifiers) {
                this.notifiers = notifiers;
            }

            public static class CategoryBean {
                /**
                 * id : 5b5607d651183107612cf15b
                 * name : IT课程
                 */

                private String id;
                private String name;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class AddressBean {
                /**
                 * id : null
                 * lat : null
                 * lng : null
                 * provice : null
                 * city : null
                 * district : null
                 * detail : 上海市闵行区恒南路
                 * linkman : null
                 * phone : null
                 * gender : null
                 * isDefault : null
                 */

                private String id;
                private String lat;
                private String lng;
                private String provice;
                private String city;
                private String district;
                private String detail;
                private String linkman;
                private String phone;
                private String gender;
                private String isDefault;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public String getLng() {
                    return lng;
                }

                public void setLng(String lng) {
                    this.lng = lng;
                }

                public String getProvice() {
                    return provice;
                }

                public void setProvice(String provice) {
                    this.provice = provice;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getDistrict() {
                    return district;
                }

                public void setDistrict(String district) {
                    this.district = district;
                }

                public String getDetail() {
                    return detail;
                }

                public void setDetail(String detail) {
                    this.detail = detail;
                }

                public String getLinkman() {
                    return linkman;
                }

                public void setLinkman(String linkman) {
                    this.linkman = linkman;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getGender() {
                    return gender;
                }

                public void setGender(String gender) {
                    this.gender = gender;
                }

                public String getIsDefault() {
                    return isDefault;
                }

                public void setIsDefault(String isDefault) {
                    this.isDefault = isDefault;
                }
            }
        }

        public static class BuyerBean {
            /**
             * id : 5b5fbfe75118310c41f3a657
             * nickName : WongLeoi
             * avatarUrl : http://p.simpleryo.com/file/459e370c35a3778974d2d67f57ce41d6
             * name : WongLeoi
             * phone : null
             * address : null
             */

            private String id;
            private String nickName;
            private String avatarUrl;
            private String name;
            private String phone;
            private String address;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public static class CoachBean {
            /**
             * id : 5b57d0e151183107612cf173
             * mid : 01
             * storeId : 5b5608c451183107612cf15f
             * creatorId : 5b5608c451183107612cf160
             * creationTime : 1532481761846
             * sequence : 0
             * no : 1532481761846
             * storeName : 微信小程序实战训练营
             * courseCount : 5
             * name : Lucy
             * nickName : Lisa
             * avatarUrl : https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/1787aa7bd09488b02e60865acfd5790e
             * studentCount : 6
             * point : 0
             * phone : 15221317052
             * chatNum : 
             * workLife : 3年-5年
             * intro : 瑜伽教练5年教龄经验
             * desc : <p>从事于瑜伽教练5年经验</p>
             * skills : [{"value":"瑜伽"},{"value":"健身"}]
             */

            private String id;
            private String mid;
            private String storeId;
            private String creatorId;
            private long creationTime;
            private int sequence;
            private String no;
            private String storeName;
            private int courseCount;
            private String name;
            private String nickName;
            private String avatarUrl;
            private int studentCount;
            private int point;
            private String phone;
            private String chatNum;
            private String workLife;
            private String intro;
            private String desc;
            private List<SkillsBean> skills;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getCreatorId() {
                return creatorId;
            }

            public void setCreatorId(String creatorId) {
                this.creatorId = creatorId;
            }

            public long getCreationTime() {
                return creationTime;
            }

            public void setCreationTime(long creationTime) {
                this.creationTime = creationTime;
            }

            public int getSequence() {
                return sequence;
            }

            public void setSequence(int sequence) {
                this.sequence = sequence;
            }

            public String getNo() {
                return no;
            }

            public void setNo(String no) {
                this.no = no;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public int getCourseCount() {
                return courseCount;
            }

            public void setCourseCount(int courseCount) {
                this.courseCount = courseCount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public int getStudentCount() {
                return studentCount;
            }

            public void setStudentCount(int studentCount) {
                this.studentCount = studentCount;
            }

            public int getPoint() {
                return point;
            }

            public void setPoint(int point) {
                this.point = point;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getChatNum() {
                return chatNum;
            }

            public void setChatNum(String chatNum) {
                this.chatNum = chatNum;
            }

            public String getWorkLife() {
                return workLife;
            }

            public void setWorkLife(String workLife) {
                this.workLife = workLife;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public List<SkillsBean> getSkills() {
                return skills;
            }

            public void setSkills(List<SkillsBean> skills) {
                this.skills = skills;
            }

            public static class SkillsBean {
                /**
                 * value : 瑜伽
                 */

                private String value;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }

        public static class CourseBean {
            /**
             * id : 5b67e0865118317498214a59
             * mid : 01
             * storeId : 5b5608c451183107612cf15f
             * creatorId : 5b5608c451183107612cf160
             * creationTime : 1533534342233
             * name : C语言基础教学
             * coverUrl : https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/c2c89b28652bef8bb342f4f396fb5c0c
             * tagId1 : 5b5607d651183107612cf15b
             * tagId2 : 5b666323511831226d406644
             * recommends : [{"value":"5b57dce351183107612cf181"}]
             * intro : <p>这是一堂生动形象的C语言课程</p>
             * distance : null
             * storeName : 微信小程序实战训练营
             * coachId : 5b57d0e151183107612cf173
             * enable : true
             * collectCount : 0
             * classCount : 5
             * goodReviewRate : 100
             * hasCollect : false
             * popular : null
             * originPrice : 4000
             * price : 1000
             * grouprice : 0
             * durations : {"startDate":"2018-08-07","endDate":"2018-09-30","data":[{"startTime":"8:00","endTime":"16:00","week":"周一"},{"startTime":"08:00","endTime":"16:00","week":"周二"},{"startTime":"8:00","endTime":"16:00","week":"周三"},{"startTime":"8:00","endTime":"16:00","week":"周四"},{"startTime":"8:00","endTime":"16:00","week":"周五"},{"startTime":"8:00","endTime":"16:00","week":"周六"}]}
             * address : {"id":null,"lat":31.0972789353362,"lng":121.50768810750878,"provice":null,"city":null,"district":null,"detail":"中国上海市闵行区陈行公路2388号 邮政编码: 201112","linkman":null,"phone":null,"gender":null,"isDefault":null}
             * loc : [121.50768810750878,31.0972789353362]
             * coach : {"id":"5b57d0e151183107612cf173","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1532481761846,"sequence":0,"no":"1532481761846","storeName":"微信小程序实战训练营","courseCount":4,"name":"Lucy","nickName":"Lisa","avatarUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/1787aa7bd09488b02e60865acfd5790e","studentCount":6,"point":0,"phone":"15221317052","chatNum":"","workLife":"3年-5年","intro":"瑜伽教练5年教龄经验","desc":"<p>从事于瑜伽教练5年经验<\/p>","skills":[{"value":"瑜伽"},{"value":"健身"}]}
             * hasCardCoupon : true
             * upperLimit : 40
             * type : series
             * maxTime : null
             * arranges : [{"id":"5b67e0865118317498214a6e","courseId":"5b67e0865118317498214a59","classDate":"2018-08-30","startTime":"2018-08-30 08:00:00","endTime":"2018-08-30 16:00:00","dateDetail":"2018-08-30 08:00-16:00"},{"id":"5b67e0865118317498214a6f","courseId":"5b67e0865118317498214a59","classDate":"2018-08-31","startTime":"2018-08-31 08:00:00","endTime":"2018-08-31 16:00:00","dateDetail":"2018-08-31 08:00-16:00"},{"id":"5b67e0865118317498214a70","courseId":"5b67e0865118317498214a59","classDate":"2018-09-01","startTime":"2018-09-01 08:00:00","endTime":"2018-09-01 16:00:00","dateDetail":"2018-09-01 08:00-16:00"},{"id":"5b67e0865118317498214a71","courseId":"5b67e0865118317498214a59","classDate":"2018-09-03","startTime":"2018-09-03 08:00:00","endTime":"2018-09-03 16:00:00","dateDetail":"2018-09-03 08:00-16:00"},{"id":"5b67e0865118317498214a72","courseId":"5b67e0865118317498214a59","classDate":"2018-09-04","startTime":"2018-09-04 08:00:00","endTime":"2018-09-04 16:00:00","dateDetail":"2018-09-04 08:00-16:00"},{"id":"5b67e0865118317498214a73","courseId":"5b67e0865118317498214a59","classDate":"2018-09-05","startTime":"2018-09-05 08:00:00","endTime":"2018-09-05 16:00:00","dateDetail":"2018-09-05 08:00-16:00"},{"id":"5b67e0865118317498214a74","courseId":"5b67e0865118317498214a59","classDate":"2018-09-06","startTime":"2018-09-06 08:00:00","endTime":"2018-09-06 16:00:00","dateDetail":"2018-09-06 08:00-16:00"},{"id":"5b67e0865118317498214a75","courseId":"5b67e0865118317498214a59","classDate":"2018-09-07","startTime":"2018-09-07 08:00:00","endTime":"2018-09-07 16:00:00","dateDetail":"2018-09-07 08:00-16:00"},{"id":"5b67e0865118317498214a76","courseId":"5b67e0865118317498214a59","classDate":"2018-09-08","startTime":"2018-09-08 08:00:00","endTime":"2018-09-08 16:00:00","dateDetail":"2018-09-08 08:00-16:00"},{"id":"5b67e0865118317498214a77","courseId":"5b67e0865118317498214a59","classDate":"2018-09-10","startTime":"2018-09-10 08:00:00","endTime":"2018-09-10 16:00:00","dateDetail":"2018-09-10 08:00-16:00"},{"id":"5b67e0865118317498214a78","courseId":"5b67e0865118317498214a59","classDate":"2018-09-11","startTime":"2018-09-11 08:00:00","endTime":"2018-09-11 16:00:00","dateDetail":"2018-09-11 08:00-16:00"},{"id":"5b67e0865118317498214a79","courseId":"5b67e0865118317498214a59","classDate":"2018-09-12","startTime":"2018-09-12 08:00:00","endTime":"2018-09-12 16:00:00","dateDetail":"2018-09-12 08:00-16:00"},{"id":"5b67e0865118317498214a7a","courseId":"5b67e0865118317498214a59","classDate":"2018-09-13","startTime":"2018-09-13 08:00:00","endTime":"2018-09-13 16:00:00","dateDetail":"2018-09-13 08:00-16:00"},{"id":"5b67e0865118317498214a7b","courseId":"5b67e0865118317498214a59","classDate":"2018-09-14","startTime":"2018-09-14 08:00:00","endTime":"2018-09-14 16:00:00","dateDetail":"2018-09-14 08:00-16:00"},{"id":"5b67e0865118317498214a7c","courseId":"5b67e0865118317498214a59","classDate":"2018-09-15","startTime":"2018-09-15 08:00:00","endTime":"2018-09-15 16:00:00","dateDetail":"2018-09-15 08:00-16:00"},{"id":"5b67e0865118317498214a7d","courseId":"5b67e0865118317498214a59","classDate":"2018-09-17","startTime":"2018-09-17 08:00:00","endTime":"2018-09-17 16:00:00","dateDetail":"2018-09-17 08:00-16:00"},{"id":"5b67e0865118317498214a7e","courseId":"5b67e0865118317498214a59","classDate":"2018-09-18","startTime":"2018-09-18 08:00:00","endTime":"2018-09-18 16:00:00","dateDetail":"2018-09-18 08:00-16:00"},{"id":"5b67e0865118317498214a7f","courseId":"5b67e0865118317498214a59","classDate":"2018-09-19","startTime":"2018-09-19 08:00:00","endTime":"2018-09-19 16:00:00","dateDetail":"2018-09-19 08:00-16:00"},{"id":"5b67e0865118317498214a80","courseId":"5b67e0865118317498214a59","classDate":"2018-09-20","startTime":"2018-09-20 08:00:00","endTime":"2018-09-20 16:00:00","dateDetail":"2018-09-20 08:00-16:00"},{"id":"5b67e0865118317498214a81","courseId":"5b67e0865118317498214a59","classDate":"2018-09-21","startTime":"2018-09-21 08:00:00","endTime":"2018-09-21 16:00:00","dateDetail":"2018-09-21 08:00-16:00"},{"id":"5b67e0865118317498214a82","courseId":"5b67e0865118317498214a59","classDate":"2018-09-22","startTime":"2018-09-22 08:00:00","endTime":"2018-09-22 16:00:00","dateDetail":"2018-09-22 08:00-16:00"},{"id":"5b67e0865118317498214a83","courseId":"5b67e0865118317498214a59","classDate":"2018-09-24","startTime":"2018-09-24 08:00:00","endTime":"2018-09-24 16:00:00","dateDetail":"2018-09-24 08:00-16:00"},{"id":"5b67e0865118317498214a84","courseId":"5b67e0865118317498214a59","classDate":"2018-09-25","startTime":"2018-09-25 08:00:00","endTime":"2018-09-25 16:00:00","dateDetail":"2018-09-25 08:00-16:00"},{"id":"5b67e0865118317498214a85","courseId":"5b67e0865118317498214a59","classDate":"2018-09-26","startTime":"2018-09-26 08:00:00","endTime":"2018-09-26 16:00:00","dateDetail":"2018-09-26 08:00-16:00"},{"id":"5b67e0865118317498214a86","courseId":"5b67e0865118317498214a59","classDate":"2018-09-27","startTime":"2018-09-27 08:00:00","endTime":"2018-09-27 16:00:00","dateDetail":"2018-09-27 08:00-16:00"},{"id":"5b67e0865118317498214a87","courseId":"5b67e0865118317498214a59","classDate":"2018-09-28","startTime":"2018-09-28 08:00:00","endTime":"2018-09-28 16:00:00","dateDetail":"2018-09-28 08:00-16:00"},{"id":"5b67e0865118317498214a88","courseId":"5b67e0865118317498214a59","classDate":"2018-09-29","startTime":"2018-09-29 08:00:00","endTime":"2018-09-29 16:00:00","dateDetail":"2018-09-29 08:00-16:00"}]
             */

            private String id;
            private String mid;
            private String storeId;
            private String creatorId;
            private long creationTime;
            private String name;
            private String coverUrl;
            private String tagId1;
            private String tagId2;
            private String intro;
            private String distance;
            private String storeName;
            private String coachId;
            private boolean enable;
            private int collectCount;
            private int classCount;
            private int goodReviewRate;
            private boolean hasCollect;
            private String popular;
            private int originPrice;
            private int price;
            private int grouprice;
            private DurationsBean durations;
            private AddressBeanX address;
            private CoachBeanX coach;
            private boolean hasCardCoupon;
            private int upperLimit;
            private String type;
            private String maxTime;
            private List<RecommendsBean> recommends;
            private List<Double> loc;
            private List<ArrangesBean> arranges;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getCreatorId() {
                return creatorId;
            }

            public void setCreatorId(String creatorId) {
                this.creatorId = creatorId;
            }

            public long getCreationTime() {
                return creationTime;
            }

            public void setCreationTime(long creationTime) {
                this.creationTime = creationTime;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCoverUrl() {
                return coverUrl;
            }

            public void setCoverUrl(String coverUrl) {
                this.coverUrl = coverUrl;
            }

            public String getTagId1() {
                return tagId1;
            }

            public void setTagId1(String tagId1) {
                this.tagId1 = tagId1;
            }

            public String getTagId2() {
                return tagId2;
            }

            public void setTagId2(String tagId2) {
                this.tagId2 = tagId2;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getCoachId() {
                return coachId;
            }

            public void setCoachId(String coachId) {
                this.coachId = coachId;
            }

            public boolean isEnable() {
                return enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }

            public int getCollectCount() {
                return collectCount;
            }

            public void setCollectCount(int collectCount) {
                this.collectCount = collectCount;
            }

            public int getClassCount() {
                return classCount;
            }

            public void setClassCount(int classCount) {
                this.classCount = classCount;
            }

            public int getGoodReviewRate() {
                return goodReviewRate;
            }

            public void setGoodReviewRate(int goodReviewRate) {
                this.goodReviewRate = goodReviewRate;
            }

            public boolean isHasCollect() {
                return hasCollect;
            }

            public void setHasCollect(boolean hasCollect) {
                this.hasCollect = hasCollect;
            }

            public String getPopular() {
                return popular;
            }

            public void setPopular(String popular) {
                this.popular = popular;
            }

            public int getOriginPrice() {
                return originPrice;
            }

            public void setOriginPrice(int originPrice) {
                this.originPrice = originPrice;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getGrouprice() {
                return grouprice;
            }

            public void setGrouprice(int grouprice) {
                this.grouprice = grouprice;
            }

            public DurationsBean getDurations() {
                return durations;
            }

            public void setDurations(DurationsBean durations) {
                this.durations = durations;
            }

            public AddressBeanX getAddress() {
                return address;
            }

            public void setAddress(AddressBeanX address) {
                this.address = address;
            }

            public CoachBeanX getCoach() {
                return coach;
            }

            public void setCoach(CoachBeanX coach) {
                this.coach = coach;
            }

            public boolean isHasCardCoupon() {
                return hasCardCoupon;
            }

            public void setHasCardCoupon(boolean hasCardCoupon) {
                this.hasCardCoupon = hasCardCoupon;
            }

            public int getUpperLimit() {
                return upperLimit;
            }

            public void setUpperLimit(int upperLimit) {
                this.upperLimit = upperLimit;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getMaxTime() {
                return maxTime;
            }

            public void setMaxTime(String maxTime) {
                this.maxTime = maxTime;
            }

            public List<RecommendsBean> getRecommends() {
                return recommends;
            }

            public void setRecommends(List<RecommendsBean> recommends) {
                this.recommends = recommends;
            }

            public List<Double> getLoc() {
                return loc;
            }

            public void setLoc(List<Double> loc) {
                this.loc = loc;
            }

            public List<ArrangesBean> getArranges() {
                return arranges;
            }

            public void setArranges(List<ArrangesBean> arranges) {
                this.arranges = arranges;
            }

            public static class DurationsBean {
                /**
                 * startDate : 2018-08-07
                 * endDate : 2018-09-30
                 * data : [{"startTime":"8:00","endTime":"16:00","week":"周一"},{"startTime":"08:00","endTime":"16:00","week":"周二"},{"startTime":"8:00","endTime":"16:00","week":"周三"},{"startTime":"8:00","endTime":"16:00","week":"周四"},{"startTime":"8:00","endTime":"16:00","week":"周五"},{"startTime":"8:00","endTime":"16:00","week":"周六"}]
                 */

                private String startDate;
                private String endDate;
                private List<DataBean> data;

                public String getStartDate() {
                    return startDate;
                }

                public void setStartDate(String startDate) {
                    this.startDate = startDate;
                }

                public String getEndDate() {
                    return endDate;
                }

                public void setEndDate(String endDate) {
                    this.endDate = endDate;
                }

                public List<DataBean> getData() {
                    return data;
                }

                public void setData(List<DataBean> data) {
                    this.data = data;
                }

                public static class DataBean {
                    /**
                     * startTime : 8:00
                     * endTime : 16:00
                     * week : 周一
                     */

                    private String startTime;
                    private String endTime;
                    private String week;

                    public String getStartTime() {
                        return startTime;
                    }

                    public void setStartTime(String startTime) {
                        this.startTime = startTime;
                    }

                    public String getEndTime() {
                        return endTime;
                    }

                    public void setEndTime(String endTime) {
                        this.endTime = endTime;
                    }

                    public String getWeek() {
                        return week;
                    }

                    public void setWeek(String week) {
                        this.week = week;
                    }
                }
            }

            public static class AddressBeanX {
                /**
                 * id : null
                 * lat : 31.0972789353362
                 * lng : 121.50768810750878
                 * provice : null
                 * city : null
                 * district : null
                 * detail : 中国上海市闵行区陈行公路2388号 邮政编码: 201112
                 * linkman : null
                 * phone : null
                 * gender : null
                 * isDefault : null
                 */

                private String id;
                private double lat;
                private double lng;
                private String provice;
                private String city;
                private String district;
                private String detail;
                private String linkman;
                private String phone;
                private String gender;
                private String isDefault;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }

                public String getProvice() {
                    return provice;
                }

                public void setProvice(String provice) {
                    this.provice = provice;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getDistrict() {
                    return district;
                }

                public void setDistrict(String district) {
                    this.district = district;
                }

                public String getDetail() {
                    return detail;
                }

                public void setDetail(String detail) {
                    this.detail = detail;
                }

                public String getLinkman() {
                    return linkman;
                }

                public void setLinkman(String linkman) {
                    this.linkman = linkman;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getGender() {
                    return gender;
                }

                public void setGender(String gender) {
                    this.gender = gender;
                }

                public String getIsDefault() {
                    return isDefault;
                }

                public void setIsDefault(String isDefault) {
                    this.isDefault = isDefault;
                }
            }

            public static class CoachBeanX {
                /**
                 * id : 5b57d0e151183107612cf173
                 * mid : 01
                 * storeId : 5b5608c451183107612cf15f
                 * creatorId : 5b5608c451183107612cf160
                 * creationTime : 1532481761846
                 * sequence : 0
                 * no : 1532481761846
                 * storeName : 微信小程序实战训练营
                 * courseCount : 4
                 * name : Lucy
                 * nickName : Lisa
                 * avatarUrl : https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/1787aa7bd09488b02e60865acfd5790e
                 * studentCount : 6
                 * point : 0
                 * phone : 15221317052
                 * chatNum : 
                 * workLife : 3年-5年
                 * intro : 瑜伽教练5年教龄经验
                 * desc : <p>从事于瑜伽教练5年经验</p>
                 * skills : [{"value":"瑜伽"},{"value":"健身"}]
                 */

                private String id;
                private String mid;
                private String storeId;
                private String creatorId;
                private long creationTime;
                private int sequence;
                private String no;
                private String storeName;
                private int courseCount;
                private String name;
                private String nickName;
                private String avatarUrl;
                private int studentCount;
                private int point;
                private String phone;
                private String chatNum;
                private String workLife;
                private String intro;
                private String desc;
                private List<SkillsBeanX> skills;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }

                public String getStoreId() {
                    return storeId;
                }

                public void setStoreId(String storeId) {
                    this.storeId = storeId;
                }

                public String getCreatorId() {
                    return creatorId;
                }

                public void setCreatorId(String creatorId) {
                    this.creatorId = creatorId;
                }

                public long getCreationTime() {
                    return creationTime;
                }

                public void setCreationTime(long creationTime) {
                    this.creationTime = creationTime;
                }

                public int getSequence() {
                    return sequence;
                }

                public void setSequence(int sequence) {
                    this.sequence = sequence;
                }

                public String getNo() {
                    return no;
                }

                public void setNo(String no) {
                    this.no = no;
                }

                public String getStoreName() {
                    return storeName;
                }

                public void setStoreName(String storeName) {
                    this.storeName = storeName;
                }

                public int getCourseCount() {
                    return courseCount;
                }

                public void setCourseCount(int courseCount) {
                    this.courseCount = courseCount;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getNickName() {
                    return nickName;
                }

                public void setNickName(String nickName) {
                    this.nickName = nickName;
                }

                public String getAvatarUrl() {
                    return avatarUrl;
                }

                public void setAvatarUrl(String avatarUrl) {
                    this.avatarUrl = avatarUrl;
                }

                public int getStudentCount() {
                    return studentCount;
                }

                public void setStudentCount(int studentCount) {
                    this.studentCount = studentCount;
                }

                public int getPoint() {
                    return point;
                }

                public void setPoint(int point) {
                    this.point = point;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getChatNum() {
                    return chatNum;
                }

                public void setChatNum(String chatNum) {
                    this.chatNum = chatNum;
                }

                public String getWorkLife() {
                    return workLife;
                }

                public void setWorkLife(String workLife) {
                    this.workLife = workLife;
                }

                public String getIntro() {
                    return intro;
                }

                public void setIntro(String intro) {
                    this.intro = intro;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public List<SkillsBeanX> getSkills() {
                    return skills;
                }

                public void setSkills(List<SkillsBeanX> skills) {
                    this.skills = skills;
                }

                public static class SkillsBeanX {
                    /**
                     * value : 瑜伽
                     */

                    private String value;

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }
            }

            public static class RecommendsBean {
                /**
                 * value : 5b57dce351183107612cf181
                 */

                private String value;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class ArrangesBean {
                /**
                 * id : 5b67e0865118317498214a6e
                 * courseId : 5b67e0865118317498214a59
                 * classDate : 2018-08-30
                 * startTime : 2018-08-30 08:00:00
                 * endTime : 2018-08-30 16:00:00
                 * dateDetail : 2018-08-30 08:00-16:00
                 */

                private String id;
                private String courseId;
                private String classDate;
                private String startTime;
                private String endTime;
                private String dateDetail;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCourseId() {
                    return courseId;
                }

                public void setCourseId(String courseId) {
                    this.courseId = courseId;
                }

                public String getClassDate() {
                    return classDate;
                }

                public void setClassDate(String classDate) {
                    this.classDate = classDate;
                }

                public String getStartTime() {
                    return startTime;
                }

                public void setStartTime(String startTime) {
                    this.startTime = startTime;
                }

                public String getEndTime() {
                    return endTime;
                }

                public void setEndTime(String endTime) {
                    this.endTime = endTime;
                }

                public String getDateDetail() {
                    return dateDetail;
                }

                public void setDateDetail(String dateDetail) {
                    this.dateDetail = dateDetail;
                }
            }
        }
    }
}
