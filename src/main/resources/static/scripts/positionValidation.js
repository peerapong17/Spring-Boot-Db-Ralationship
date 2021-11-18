window.addEventListener("DOMContentLoaded", (evt => {
    console.log("Dom is ready")
    var form = document.querySelector(".needs-validation")
    var positionInput = document.getElementById("name")

    const showError = (input, message) => {
        input.classList.remove("is-valid")
        input.classList.add("is-invalid")
        input.nextSibling.nextSibling.innerHTML = message
    }

    const showSuccess = (input) => {
        input.classList.remove("is-invalid")
        input.classList.add("is-valid")
    }

    const validateName = (input) => {
        if(input.value == ""){
            showError(input,"Position name is required")
        } else if (!input.value.match(/^[A-Z]+$/)){
            showError(input, "Position name should only be capital letter and cannot contain special character")
        }else if (input.value.length < 2){
            showError(input, "Position name should be at least 3 characters long")
        } else {
            showSuccess(input)
            return true
        }
    }

    form.addEventListener("submit", (e)=>{
        e.preventDefault()
        e.stopImmediatePropagation()

        const validName = validateName(positionInput)

        if(validName){
            form.submit()
        }
    })
}))