package org.tinlone.demo.rxjavasample.bean;

import java.util.List;

public class StudentBean {

    /**
     * state : 0
     * msg : success
     * data : {"id":1,"account":"996847470","name":"张三","nick":"三张","score":20,"questionCount":0,"phone":"1315454252","school":"武汉大学","major":"原子物理","franchiseeId":0,"franchiseeName":"武大","favoriteTag":[{"id":11,"displayName":"考研政治","name":"kyzz"}],"state":1}
     */

    private int state;
    private String msg;
    private DataBean data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * account : 996847470
         * name : 张三
         * nick : 三张
         * score : 20
         * questionCount : 0
         * phone : 1315454252
         * school : 武汉大学
         * major : 原子物理
         * franchiseeId : 0
         * franchiseeName : 武大
         * favoriteTag : [{"id":11,"displayName":"考研政治","name":"kyzz"}]
         * state : 1
         */

        private int id;
        private String account;
        private String name;
        private String nick;
        private int score;
        private int questionCount;
        private String phone;
        private String school;
        private String major;
        private int franchiseeId;
        private String franchiseeName;
        private int state;
        private List<FavoriteTagBean> favoriteTag;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getQuestionCount() {
            return questionCount;
        }

        public void setQuestionCount(int questionCount) {
            this.questionCount = questionCount;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public int getFranchiseeId() {
            return franchiseeId;
        }

        public void setFranchiseeId(int franchiseeId) {
            this.franchiseeId = franchiseeId;
        }

        public String getFranchiseeName() {
            return franchiseeName;
        }

        public void setFranchiseeName(String franchiseeName) {
            this.franchiseeName = franchiseeName;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public List<FavoriteTagBean> getFavoriteTag() {
            return favoriteTag;
        }

        public void setFavoriteTag(List<FavoriteTagBean> favoriteTag) {
            this.favoriteTag = favoriteTag;
        }

        public static class FavoriteTagBean {
            /**
             * id : 11
             * displayName : 考研政治
             * name : kyzz
             */

            private int id;
            private String displayName;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "FavoriteTagBean{" +
                        "id=" + id +
                        ", displayName='" + displayName + '\'' +
                        ", name='" + name + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", account='" + account + '\'' +
                    ", name='" + name + '\'' +
                    ", nick='" + nick + '\'' +
                    ", score=" + score +
                    ", questionCount=" + questionCount +
                    ", phone='" + phone + '\'' +
                    ", school='" + school + '\'' +
                    ", major='" + major + '\'' +
                    ", franchiseeId=" + franchiseeId +
                    ", franchiseeName='" + franchiseeName + '\'' +
                    ", state=" + state +
                    ", favoriteTag=" + favoriteTag +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "StudentBean{" +
                "state=" + state +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
