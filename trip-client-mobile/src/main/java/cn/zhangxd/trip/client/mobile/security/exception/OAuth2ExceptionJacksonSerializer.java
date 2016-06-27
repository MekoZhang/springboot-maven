package cn.zhangxd.trip.client.mobile.security.exception;

import cn.zhangxd.trip.client.mobile.constant.Message;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import java.io.IOException;
import java.util.Map.Entry;

public class OAuth2ExceptionJacksonSerializer extends StdSerializer<OAuth2Exception> {

    public OAuth2ExceptionJacksonSerializer() {
        super(OAuth2Exception.class);
    }

	@Override
	public void serialize(OAuth2Exception value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
			JsonProcessingException {
        jgen.writeStartObject();
		jgen.writeStringField(Message.RETURN_FIELD_ERROR, value.getOAuth2ErrorCode());
		jgen.writeStringField(Message.RETURN_FIELD_ERROR_DESC, value.getMessage());
		if (value.getAdditionalInformation()!=null) {
			for (Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
				String key = entry.getKey();
				String add = entry.getValue();
				jgen.writeStringField(key, add);				
			}
		}
        jgen.writeEndObject();
	}

}
