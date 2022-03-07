

resource "google_compute_instance" "default"{
    count=length(var.name_count)

    name="test-${count.index+1}"
    machine_type = "${var.machine_type["dev"]}"
    zone=var.zone

    boot_disk {
      initialize_params {
          image="${var.image}"
      }
    }
    network_interface {
      network="default"

    }

}


output "machine_type" {
    value = "${google_compute_instance.default.*.machine_type}"
}
# output "zone" {
#     value = google_compute_instance.default.*.zone
# }
# output "image" {
#   value=google_compute_instance.default.*.image
# }