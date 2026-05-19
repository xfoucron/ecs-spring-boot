# ecs-spring-boot

[Elastic Common Schema (ECS)](https://www.elastic.co/docs/reference/ecs) on Spring Boot.

### Logging

With ECS (and JSON logging in general), you can add custom properties to make debugging easier.

This allows you to have both an explicit message (for example: `Post not found`) and an even type (for example:
`post_not_found`) so you can build graphs and track how often specific events occur.

*some data has been truncated or removed from the examples below*

#### If a post is not found:

```json
{
  "@timestamp": "2026-05-19T22:06:32.144571057Z",
  "log": {
    "level": "WARN",
    "logger": "fr.origisoft.ecsspringboot.infrastructure.controller.PostController"
  },
  "message": "Post not found",
  "post": {
    "id": "194b8f4f-da82-4a25-a4e9-a6b7f6dff168"
  },
  "event": {
    "action": "post_get",
    "reason": "post_not_found",
    "outcome": "failure"
  },
  "error": {
    "type": "PostNotFoundException"
  }
}
```

#### If the message is too long :

```json
{
  "@timestamp": "2026-05-19T22:06:29.209163800Z",
  "log": {
    "level": "WARN",
    "logger": "fr.origisoft.ecsspringboot.infrastructure.controller.PostController"
  },
  "message": "Post message exceeds max length",
  "event": {
    "action": "post_create",
    "reason": "message_too_long",
    "outcome": "failure"
  },
  "post": {
    "message": {
      "max_length": 100,
      "actual_length": 117
    }
  },
  "error": {
    "type": "PostMessageTooLongException"
  }
}
```
