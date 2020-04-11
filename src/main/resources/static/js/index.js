function changeArticleSubmit(type) {
    let changeArticleForm = document.form_change_article;
    let checks = document.getElementsByName("article_check");
    let articleId = null;

    for (let i = 0; i < checks.length; i++){
        if (checks[i].checked){
            articleId = checks[i].getAttribute("data-id");
            break;
        }
    }

    if (articleId == null) {
        alert("記事を選択してください");
        return;
    }

    switch (type) {
        case "update":
            changeArticleForm.action = "/edit/" + articleId;
            changeArticleForm.submit();
            break;
        default:
            break;
    }

}