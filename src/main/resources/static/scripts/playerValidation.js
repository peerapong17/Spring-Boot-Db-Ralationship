window.addEventListener("DOMContentLoaded", (evt => {
    console.log("Dom is ready")
    var form = document.querySelector(".needs-validation")
    var countryInput = document.getElementById("name")
    var countrySelectInput = document.getElementById("country")
    var clubSelectInput = document.getElementById("club")
    var positionContainer = document.getElementById("positionContainer")
    var positionList = document.querySelectorAll(".positions")
    // var invalidFeedback = positionContainer.querySelector(".invalid-feedback")
    var invalidFeedback = document.querySelector(".invalid-form-checkbox")

    console.log(positionList)

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

    const validateSelectInput = (input, defaultType) => {
        if (input.value == defaultType) {
            showError(input, `${defaultType} is required`)
        } else {
            showSuccess(input)
            return true
        }
    }

    const validatePosition = (input) => {
        console.log(input)
        var positionIds = []
        positionList.forEach(position => {
            console.log(position)
            if (position.checked == true) {
                positionIds.push(position.checked)
            }
        })
        console.log(positionIds)
        if (positionIds.length == 0) {
            input.classList.remove("d-none")
            input.classList.add("d-block")
            input.innerHTML = 'Player should have at least 1 position'
        } else {
            input.classList.remove("d-block")
            input.classList.add("d-none")
            input.innerHTML = ""
            return true
        }
    }

    form.addEventListener("submit", (e) => {
        e.preventDefault()
        e.stopImmediatePropagation()

        const validName = validateName(countryInput)
        const validContinent = validateSelectInput(countrySelectInput, "Country")
        const validCountry = validateSelectInput(clubSelectInput, "Club")
        const validPositions = validatePosition(invalidFeedback)

        if (validName && validContinent && validCountry && validPositions) {
            form.submit()
        }
    })
}))