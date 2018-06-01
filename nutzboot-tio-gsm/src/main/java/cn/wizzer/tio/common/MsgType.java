package cn.wizzer.tio.common;

/**
 * Created by JF on 2018/5/14.
 */
public enum MsgType {
    CONNECT(1),//通信初始化
    PING(12),//心跳
    COUNTREQ(32),//统计数据
    COUNTACK(33),//统计数据
    DATAREQ(115),//历史数据
    DATAACK(116),//历史数据
    NULL(0);//其他数据不处理


    private final int value;

    MsgType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static MsgType valueOf(int type) {
        for (MsgType t : values()) {
            if (t.value == type) {
                return t;
            }
        }
        return NULL;
    }
    }
