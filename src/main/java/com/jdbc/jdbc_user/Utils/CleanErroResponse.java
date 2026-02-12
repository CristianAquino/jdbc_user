package com.jdbc.jdbc_user.Utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CleanErroResponse {
    public static String transform(String msg){
        return msg.replaceAll("(ORA-\\d+: |\\n.*|\\r.*)","");
    }
}
