resource "google_container_cluster" "primary" {
  name               = "primary"
  location           = "us-central1-a"
  initial_node_count = 3

  addons_config {
    http_load_balancing {
      disabled = true
    }
    horizontal_pod_autoscaling {
      disabled = true
    }
  }

  node_config {
    service_account = google_service_account.kubernetes_node.email
  }

  depends_on = [google_project_service.compute, google_project_service.container]
}

