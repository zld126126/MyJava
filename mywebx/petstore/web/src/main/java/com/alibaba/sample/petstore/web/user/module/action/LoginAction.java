/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.sample.petstore.web.user.module.action;

import static com.alibaba.sample.petstore.web.common.PetstoreConstant.*;

import javax.servlet.http.HttpSession;

import com.alibaba.citrus.service.form.CustomErrors;
import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormField;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.util.StringUtil;
import com.alibaba.sample.petstore.biz.UserManager;
import com.alibaba.sample.petstore.dal.dataobject.User;
import com.alibaba.sample.petstore.web.common.PetstoreUser;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginAction {
    @Autowired
    private UserManager userManager;

    public void doLogin(@FormGroup("login") User user,
                        @FormField(name = "loginError", group = "login") CustomErrors err, Navigator nav,
                        HttpSession session, ParameterParser params) throws Exception {
        user = userManager.login(user.getUserId(), user.getPassword());

        if (user != null) {
            // 在session中创建petstoreUser对象
            PetstoreUser petstoreUser = (PetstoreUser) session.getAttribute(PETSTORE_USER_SESSION_KEY);

            if (petstoreUser == null || petstoreUser.hasLoggedIn()) {
                petstoreUser = new PetstoreUser();
            }

            petstoreUser.upgrade(user.getUserId(), user.getRoles());

            session.setAttribute(PETSTORE_USER_SESSION_KEY, petstoreUser);

            // 跳转到return页面
            redirectToReturnPage(nav, params);
        } else {
            err.setMessage("invalidUserOrPassword");
        }
    }

    public void doLogout(HttpSession session, Navigator nav, ParameterParser params) throws Exception {
        // 清除session中的user
        session.removeAttribute(PETSTORE_USER_SESSION_KEY);

        // 跳转到return页面
        redirectToReturnPage(nav, params);
    }

    private void redirectToReturnPage(Navigator nav, ParameterParser params) {
        String returnURL = params.getString("return");

        if (StringUtil.isEmpty(returnURL)) {
            nav.redirectTo(LOGIN_RETURN_DEFAULT_LINK);
        } else {
            nav.redirectToLocation(returnURL);
        }
    }
}
