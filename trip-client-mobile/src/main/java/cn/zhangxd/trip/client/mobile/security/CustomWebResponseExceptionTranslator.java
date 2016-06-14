/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.zhangxd.trip.client.mobile.security;

import cn.zhangxd.trip.client.mobile.constant.Message;
import cn.zhangxd.trip.client.mobile.constant.ReturnCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.io.IOException;

/**
 * @author Dave Syer
 */
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {

    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {

        // Try to extract a SpringSecurityException from the stacktrace
        Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);
        Exception ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(
                OAuth2Exception.class, causeChain);

        if (ase != null) {
            return handleOAuth2Exception((OAuth2Exception) ase);
        }

        ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class,
                causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new UnauthorizedException(e.getMessage(), e));
        }

        ase = (AccessDeniedException) throwableAnalyzer
                .getFirstThrowableOfType(AccessDeniedException.class, causeChain);
        if (ase instanceof AccessDeniedException) {
            return handleOAuth2Exception(new ForbiddenException(ase.getMessage(), ase));
        }

        ase = (HttpRequestMethodNotSupportedException) throwableAnalyzer
                .getFirstThrowableOfType(HttpRequestMethodNotSupportedException.class, causeChain);
        if (ase instanceof HttpRequestMethodNotSupportedException) {
            return handleOAuth2Exception(new MethodNotAllowed(ase.getMessage(), ase));
        }

        return handleOAuth2Exception(new ServerErrorException(e.getMessage(), e));

    }

    private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) throws IOException {

        int status = e.getHttpErrorCode();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        if (status == HttpStatus.UNAUTHORIZED.value() || (e instanceof InsufficientScopeException)) {
            headers.set("WWW-Authenticate", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
        }

        e.addAdditionalInformation(Message.RETURN_FIELD_CODE, getBusinessCode(e.getOAuth2ErrorCode()));

        ResponseEntity<OAuth2Exception> response = new ResponseEntity<OAuth2Exception>(e, headers,
                HttpStatus.valueOf(status));

        return response;

    }

    private String getBusinessCode(String errorCode) {
        if (OAuth2Exception.INVALID_CLIENT.equals(errorCode)) {
            return ReturnCode.CODE_INVALID_CLIENT;
        } else if (OAuth2Exception.UNAUTHORIZED_CLIENT.equals(errorCode) || UNAUTHORIZED_USER.equals(errorCode)) {
            return ReturnCode.CODE_UNAUTHORIZED_CLIENT;
        } else if (OAuth2Exception.INVALID_GRANT.equals(errorCode)) {
            return ReturnCode.CODE_INVALID_GRANT;
        } else if (OAuth2Exception.INVALID_SCOPE.equals(errorCode)) {
            return ReturnCode.CODE_INVALID_SCOPE;
        } else if (OAuth2Exception.INVALID_TOKEN.equals(errorCode)) {
            return ReturnCode.CODE_INVALID_TOKEN;
        } else if (OAuth2Exception.INVALID_REQUEST.equals(errorCode)) {
            return ReturnCode.CODE_INVALID_REQUEST;
        } else if (OAuth2Exception.UNSUPPORTED_GRANT_TYPE.equals(errorCode)) {
            return ReturnCode.CODE_UNSUPPORTED_GRANT_TYPE;
        } else if (OAuth2Exception.UNSUPPORTED_RESPONSE_TYPE.equals(errorCode)) {
            return ReturnCode.CODE_UNSUPPORTED_RESPONSE_TYPE;
        } else if (OAuth2Exception.ACCESS_DENIED.equals(errorCode)) {
            return ReturnCode.CODE_FORBIDDEN;
        } else if (SERVER_ERROR.equals(errorCode)) {
            return ReturnCode.CODE_INTERNAL_SERVER_ERROR;
        } else if (UNAUTHORIZED.equals(errorCode)) {
            return ReturnCode.CODE_UNAUTHORIZED;
        } else if (METHOD_NOT_ALLOWED.equals(errorCode)) {
            return ReturnCode.CODE_METHOD_NOT_ALLOWED;
        } else {
            return ReturnCode.CODE_UNAUTHORIZED;
        }
    }

    public void setThrowableAnalyzer(ThrowableAnalyzer throwableAnalyzer) {
        this.throwableAnalyzer = throwableAnalyzer;
    }

    public static final String SERVER_ERROR = "server_error";
    public static final String UNAUTHORIZED = "unauthorized";
    public static final String METHOD_NOT_ALLOWED = "method_not_allowed";
    public static final String UNAUTHORIZED_USER = "unauthorized_user";


    @SuppressWarnings("serial")
    private static class ForbiddenException extends OAuth2Exception {

        public ForbiddenException(String msg, Throwable t) {
            super(msg, t);
        }

        public String getOAuth2ErrorCode() {
            return ACCESS_DENIED;
        }

        public int getHttpErrorCode() {
            return 403;
        }

    }

    @SuppressWarnings("serial")
    private static class ServerErrorException extends OAuth2Exception {

        public ServerErrorException(String msg, Throwable t) {
            super(msg, t);
        }

        public String getOAuth2ErrorCode() {
            return SERVER_ERROR;
        }

        public int getHttpErrorCode() {
            return 500;
        }

    }

    @SuppressWarnings("serial")
    private static class UnauthorizedException extends OAuth2Exception {

        public UnauthorizedException(String msg, Throwable t) {
            super(msg, t);
        }

        public String getOAuth2ErrorCode() {
            return UNAUTHORIZED;
        }

        public int getHttpErrorCode() {
            return 401;
        }

    }

    @SuppressWarnings("serial")
    private static class MethodNotAllowed extends OAuth2Exception {

        public MethodNotAllowed(String msg, Throwable t) {
            super(msg, t);
        }

        public String getOAuth2ErrorCode() {
            return METHOD_NOT_ALLOWED;
        }

        public int getHttpErrorCode() {
            return 405;
        }

    }
}
