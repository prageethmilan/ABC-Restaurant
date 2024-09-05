// ** Logo
import loading from "@src/assets/images/loading/loading.gif"

const SpinnerComponent = () => {
  return (
    <div className="fallback-spinner app-loader">
      <img className="fallback-logo" width={180} src={loading} alt="loading..." />
    </div>
  );
};

export default SpinnerComponent;
