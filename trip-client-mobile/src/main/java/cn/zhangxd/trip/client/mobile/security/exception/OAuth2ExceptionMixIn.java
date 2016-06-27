package cn.zhangxd.trip.client.mobile.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using=OAuth2ExceptionJacksonSerializer.class)
@JsonDeserialize(using = OAuth2ExceptionJacksonDeserializer.class)
public abstract class OAuth2ExceptionMixIn {

}