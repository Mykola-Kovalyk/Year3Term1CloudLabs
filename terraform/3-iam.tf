resource "google_service_account" "deployed_instance_account" {
  account_id   = "deployed-instance"
  display_name = "Account used my mysqlspringlab when running"
}

resource "google_project_iam_binding" "storage_admin_binding" {
  project = local.project_id
  role    = "roles/cloudsql.admin"

  members = [
    "serviceAccount:${google_service_account.deployed_instance_account.email}"
  ]
}

resource "google_project_iam_binding" "secret_manager_admin_binding" {
  project = local.project_id
  role    = "roles/secretmanager.admin"

  members = [
    "serviceAccount:${google_service_account.deployed_instance_account.email}"
  ]
}

resource "google_service_account" "github_actions_account" {
  account_id   = "github-actions"
  display_name = "Account used by Github Actions to deploy to GKE"
}

resource "google_project_iam_binding" "github_actions_editor_binding" {
  project = local.project_id
  role    = "roles/editor"

  members = [
    "serviceAccount:${google_service_account.github_actions_account.email}"
  ]
}



resource "google_project_iam_binding" "deployed_instance_sql_admin_binding" {
  project = local.project_id
  role    = "roles/cloudsql.admin"

  members = [
    "serviceAccount:${google_service_account.deployed_instance_account.email}"
  ]
}


resource "google_service_account" "kubernetes_node" {
  account_id   = "kubernetes-node"
  display_name = "Account used by GKE nodes"
}

resource "google_project_iam_binding" "kubernetes_node_cluster_binding" {
  project = local.project_id
  role    = "roles/container.clusterViewer"

  members = [
    "serviceAccount:${google_service_account.kubernetes_node.email}"
  ]
}

resource "google_project_iam_binding" "kubernetes_node_artifact_registry_binding" {
  project = local.project_id
  role    = "roles/artifactregistry.reader"

  members = [
    "serviceAccount:${google_service_account.kubernetes_node.email}"
  ]
}

