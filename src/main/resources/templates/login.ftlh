<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <@l.login path="/login"/>
    <a href="/registration">Create new account</a>
</@c.page>