const viewContactModal = document.getElementById("view_contact_modal");

const option = {
  placement: "bottom-right",
  backdrop: "dynamic",
  backdropClasses: "bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40",
  closable: true,
  onHide: () => {
    console.log("modal is hidden");
  },
  onShow: () => {
    console.log("modal is shown");
  },
  onToggle: () => {
    console.log("modal is toggled");
  },
};

const instanceOptions = {
  id: "modalEl",
  override: true,
};

const contactModal = new Modal(viewContactModal, option, instanceOptions);

function viewContactDetails() {
  contactModal.show();
}

function hideContactDetails() {
  contactModal.hide();
}

async function loadContactData(contactId) {
  console.log("Contact ID: ", contactId);
  try {
    const response = await fetch(`/api/contacts/${contactId}`);

    if (!response.ok) {
      throw new Error("Network response was not ok " + response.statusText);
    }

    const data = await response.json();

    console.log("Contact data: ", data);

    // Ensure these elements exist in your HTML
    document.getElementById("contact_name").innerText = data.name || "N/A";
    document.getElementById("contact_email").innerText = data.email || "N/A";
    document.getElementById("contact_phone").innerText = data.phoneNo || "N/A";
    document.getElementById("contact_address").innerText =
      data.address || "N/A";
    document.getElementById("contact_instagram").innerText =
      data.instagram || "N/A";
    document.getElementById("contact_linkedin").innerText =
      data.linkedin || "N/A";
  } catch (error) {
    console.error("Error fetching contact data: ", error);
  }
}
