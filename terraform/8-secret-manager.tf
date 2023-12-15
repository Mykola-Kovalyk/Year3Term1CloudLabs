
resource "google_secret_manager_secret" "secret_mysql_user" {
  secret_id = "mysql-user"

  replication {
    user_managed {
      replicas {
        location = "us-central1"
      }
    }
  }

  depends_on = [google_project_service.secretmanager]
}

resource "google_secret_manager_secret" "secret_mysql_password" {
  secret_id = "mysql-password"

  replication {
    user_managed {
      replicas {
        location = "us-central1"
      }
    }
  }

  depends_on = [google_project_service.secretmanager]
}



