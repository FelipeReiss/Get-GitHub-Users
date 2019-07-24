package com.githubusers.model

/*
    FYI: API Github JSON response
[
  {
    "login": "",
    "id": 0,
    "node_id": "",
    "avatar_url": "https://avatars0.githubusercontent.com/u/...",
    "gravatar_id": "",
    "url": "https://api.github.com/users/...",
    "html_url": "https://github.com/...",
    "followers_url": "https://api.github.com/users/..../followers",
    "following_url": "https://api.github.com/users/..../following{/other_user}",
    "gists_url": "https://api.github.com/users/..../gists{/gist_id}",
    "starred_url": "https://api.github.com/users/..../starred{/owner}{/repo}",
    "subscriptions_url": "https://api.github.com/users/..../subscriptions",
    "organizations_url": "https://api.github.com/users/..../orgs",
    "repos_url": "https://api.github.com/users/..../repos",
    "events_url": "https://api.github.com/users/..../events{/privacy}",
    "received_events_url": "https://api.github.com/users/..../received_events",
    "type": "",
    "site_admin": false/true
    "name": "",
    "company": null,
    "blog": "",
    "location": "",
    "email": null,
    "hireable": null,
    "bio": "",
    "public_repos": 0
    "public_gists": 0,
    "followers": 0
    "following": 0
    "created_at": "",
    "updated_at": ""
  },
  { ...
  },
  .
  .
  .
]
 */

// Data Class with initial fields to users list
data class GithubUserItemList (val id: Int, val login: String, val url: String = "")