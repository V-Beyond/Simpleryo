package com.simpleryo.leyotang.bean;

import java.util.List;

/**
 * @ClassNname：BuyedCouseListBean.java
 * @Describe  我的课程列表实体类
 * @author huanglei
 * @time 2018/4/28 12:19
 */
public class BuyedCouseListBean extends BaseResult {


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

        private String courseId;
        private String orderId;
        private String name;
        private String coverUrl;
        private DurationsBean durations;
        private int totalClass;
        private int completeClass;
        private String status;

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
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

        public DurationsBean getDurations() {
            return durations;
        }

        public void setDurations(DurationsBean durations) {
            this.durations = durations;
        }

        public int getTotalClass() {
            return totalClass;
        }

        public void setTotalClass(int totalClass) {
            this.totalClass = totalClass;
        }

        public int getCompleteClass() {
            return completeClass;
        }

        public void setCompleteClass(int completeClass) {
            this.completeClass = completeClass;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static class DurationsBean {
            /**
             * startDate : 2018-04-22
             * endDate : 2018-05-30
             * data : [{"startTime":"8:00","endTime":"16:00","week":"周一"},{"startTime":"8:00","endTime":"16:00","week":"周二"}]
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
    }
}
