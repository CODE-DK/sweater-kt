<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    Create new user: ${message!""}
    <@l.login path="/registration"/>
    <a href="/login">Log In</a>
</@c.page>


