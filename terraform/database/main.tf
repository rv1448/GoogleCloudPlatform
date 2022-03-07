resource "google_sql_database_instance" "gcp_database" {
    name = var.name
    region = var.region
    database_version=var.database_version
    deletion_protection = false
    settings {
      tier=var.tier
      disk_size=var.disk_size
      # replication_type=var.replication_type
      activation_policy = var.activation_policy

    }
}



resource "google_sql_user" "admin"{
  count=1
  name = var.name
  host= var.user_host
  password = var.password
  instance = google_sql_database_instance.gcp_database.name
}