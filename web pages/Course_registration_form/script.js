// Auto-calculate total fee
let subjects = document.querySelectorAll(".subject");
let totalBox = document.getElementById("total");
let messageBox = document.getElementById("resultMessage");

subjects.forEach(item => {
    item.addEventListener("change", () => {
        let total = 0;

        subjects.forEach(sub => {
            if (sub.checked) {
                total += parseInt(sub.value);
            }
        });

        totalBox.innerText = "₹" + total;
    });
});

// Optional: form submit
document.getElementById("regForm").addEventListener("submit", function(e){
    e.preventDefault();
      e.preventDefault();

    let selectedSubjects = [];
    let totalFee = 0;

    subjects.forEach(sub => {
        if (sub.checked) {

            // Get subject name from the label text
            let subjectName = sub.parentElement.innerText.trim();
            selectedSubjects.push(subjectName);

            totalFee += parseInt(sub.value);
        }
    });

    if (selectedSubjects.length === 0) {
        messageBox.style.display = "block";
        messageBox.style.background ="#ffe0e0";
        messageBox.style.borderLeft = "5px solid red";
        messageBox.innerText = "Please select at least one subject.";
        return;
    }

    let studentName = document.getElementById("name").value;

    let message =
        "Student Name: " + studentName + "\n\n" +
        "Selected Subjects:\n- " + selectedSubjects.join("\n- ") + "\n\n" +
        "Total Fee: ₹" + totalFee;

        messageBox.style.display = "block";
        messageBox.innerText = message;

});