<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <@l.logout/>
        <span><a href="/user">User list</a></span>
    </div>
    <div>
        <form method="post" action="/add">
            <label>Text: <input type="text" name="text" placeholder="Type you text here..."></label>
            <label>Tag: <input type="text" name="tag" placeholder="Tag"></label>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add Message</button>
        </form>
    </div>
    <div>Messages</div>
    <div>
        <form method="get" action="/main">
            <label>Search: <input type="text" name="filter" value="${filter}"></label>
            <button type="submit">Find</button>
        </form>
    </div>
    <#list messages as msg>
        <div>
            <b>${msg.id}</b>
            <span>${msg.text}</span>
            <i>${msg.tag}</i>
            <strong>${msg.authorName}</strong>
        </div>
    <#else >
        <div>No messages!</div>
    </#list>
</@c.page>