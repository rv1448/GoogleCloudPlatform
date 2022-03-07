resource "google_storage_bucket" "staging_bucket" {
  name = var.bucket_name
  location = "US"
  force_destroy = true
  }
 

resource "google_bigquery_dataset" "staging" {
  dataset_id                  = "staging"
  friendly_name               = "test"
  description                 = "This is a test description"
  location                    = "US"
  default_table_expiration_ms = 3600000
  delete_contents_on_destroy = true
  labels = {
    env = "default"
  }


}

 

resource "null_resource" "name" {

  provisioner "local-exec" {
      command = "gsutil cp -r ${var.folder_path} gs://${var.bucket_name}/"
  }

  depends_on = [
    google_storage_bucket.staging_bucket
  ]
}


#   triggers = {

#    file_hashes = jsonencode({

#    for fn in fileset(var.folder_path, "**") :

#    fn => filesha256("${var.folder_path}/${fn}")

#    })

#  }


# resource "google_service_account" "bqowner" {
#   account_id = "bqowner"
# }