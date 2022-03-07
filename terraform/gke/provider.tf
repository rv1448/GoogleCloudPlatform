provider "google" {
    credentials = file(var.cred_json)
    project = var.project_id
    region=var.region
    zone=var.zone
}



provider "google-beta" {
    credentials = file(var.cred_json)
    project = var.project_id
    region=var.region
    zone=var.zone
}