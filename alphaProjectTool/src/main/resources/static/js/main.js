document.addEventListener("DOMContentLoaded", () => {
    const rows = document.querySelectorAll("table tbody tr[data-name]");
    const labels = [];
    const estimated = [];
    const actual = [];

    rows.forEach(row => {
        labels.push(row.dataset.name);
        estimated.push(parseFloat(row.dataset.est || "0"));
        actual.push(parseFloat(row.dataset.actual || "0"));
    });

    const canvas = document.getElementById("hoursChart");
    if (!canvas || labels.length === 0) {
        return;
    }

    const ctx = canvas.getContext("2d");


    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'Estimerede timer',
                    data: estimated
                },
                {
                    label: 'Registrerede timer',
                    data: actual
                }
            ]
        }
    });
});
