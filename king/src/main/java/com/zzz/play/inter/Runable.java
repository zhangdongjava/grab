package com.zzz.play.inter;

/**
 * Created by dell_2 on 2016/11/2.
 */
public interface Runable {

    boolean run();

    String getFileName();

    /**
     * 是否清理
     * @return
     */
    boolean isClear();

    class RunConfig{
        /**
         * 是否清理
         */
        private boolean clear;
        /**
         * 运行次数
         */
        private int count = -1;

        public boolean isClear() {
            return clear;
        }

        public void setClear(boolean clear) {
            this.clear = clear;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("RunConfig{");
            sb.append("clear=").append(clear);
            sb.append(", count=").append(count);
            sb.append('}');
            return sb.toString();
        }
    }

}
