package com.zjn.pojo.po;

/**
 * @BelongsProject: greenhand
 * @BelongsPackage: com.zjn.pojo.po
 * @Author: yons
 * @CreateTime: 2020-06-19 09:32
 * @Description:
 */
public class Greeting {
    private final long id;
    private  final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
