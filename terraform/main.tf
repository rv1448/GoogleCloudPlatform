

terraform {
  backend "local"{
      path="state/terraform.tfstate"
  }
}

provider "google" {
    credentials = file(var.cred_json)
    project = var.project_id
    region=var.region
    zone=var.zone
}

resource "google_compute_network" "vpc_network" {
  name = "terraform-network"
}

resource "google_storage_bucket" "staging_bucket" {
  name = "staging_bucket_test_123234234"
  location = "US"
  force_destroy = true
  }

output "tf_bucket_name" {
  value = google_storage_bucket.staging_bucket
}

 