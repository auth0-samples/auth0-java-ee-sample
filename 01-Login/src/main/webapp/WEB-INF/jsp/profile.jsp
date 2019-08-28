<!DOCTYPE html>
<html lang="en" class="h-100">

<%@include file="fragments/header.jspf"%>

<body class="h-100">
<div class="h-100 d-flex flex-column">
    <%@include file="fragments/navbar.jspf"%>

    <div id="page" layout:fragment="content">
        <div class="container">
            <div class="mt-5">
                <div class="container">
                    <div class="row align-items-center profile-header">
                        <div class="col-md-2 mb-3">
                            <img src="${profile.get('picture').asString()}" class="rounded-circle img-fluid profile-picture"/>
                        </div>
                        <div class="col-md text-center text-md-left">
                            <h2>${profile.get('name').asString()}</h2>
                            <p class="lead text-muted">${profile.get('email').asString()}</p>
                        </div>
                    </div>

                    <div class="row">
                        <pre class="rounded"><code class="json" id="profileJsonEl">${profileJson}</code></pre>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include file="fragments/footer.jspf"%>
    <%@include file="fragments/scripts.jspf"%>
</div>
</body>
</html>
