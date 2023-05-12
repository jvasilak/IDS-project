from tensorflow.keras.models import load_model
from tensorflow.keras.models import Sequential
from tensorflow.keras.models import Model

model = load_model('../data/destinations_ltsm.h5')
weights = model.get_weights()
new_model = Sequential(model.layers)
inputs = model.inputs
outputs = model.outputs
new_model = Model(inputs, outputs)
new_model.set_weights(weights)
new_model.save_weights('my_model_weights.h5')