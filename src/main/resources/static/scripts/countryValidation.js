window.addEventListener("DOMContentLoaded", (evt => {
    console.log("Dom is ready")
    var form = document.querySelector(".needs-validation")
    var countryInput = document.getElementById("name")
    var continentSelectInput = document.getElementById("continent")

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
        if (input.value == "") {
            showError(input, "Continent name is required")
        } else if (!input.value.match(/^[a-zA-Z\s]+$/)) {
            showError(input, "Continent name cannot contain special character")
        } else if (input.value.length < 3) {
            showError(input, "Continent name should be at least 3 characters long")
        } else {
            showSuccess(input)
            return true
        }
    }

    const validateContinent = (input, defaultType) => {
        console.log(input.value)
        if(input.value == defaultType){
            showError(input, "Continent is required")
        } else {
            showSuccess(input)
            return true
        }
    }

    form.addEventListener("submit", (e) => {
        e.preventDefault()
        e.stopImmediatePropagation()

        const validName = validateName(countryInput)
        const validContinent = validateContinent(continentSelectInput, "Continent")

        if (validName && validContinent) {
            form.submit()
        }
    })
}))