import React, {Component} from "react";
import { Button, StyleSheet, Text, View } from "react-native";
import DateTimePicker from "react-native-modal-datetime-picker";

export default class TimePicker extends Component {
  state = {
    isStartTimePickerVisible: false,
    isEndTimePickerVisible: false,
    selectedStartTime: "",
    selectedEndTime: ""
  };

  showStartTimePicker = () => {
    this.setState({ isStartTimePickerVisible: true });
  };

  hideStartTimePicker = () => {
    this.setState({ isStartTimePickerVisible: false });
  };

  showEndTimePicker = () => {
    this.setState({ isEndTimePickerVisible: true });
  };

  hideEndTimePicker = () => {
    this.setState({ isEndTimePickerVisible: false });
  };

  handleStartTimePicked = startTime => {
    this.setState({ selectedStartTime: startTime.toString() });
    this.hideStartTimePicker();
    this.props.setStartTimeFromParent(startTime.toString());
  };

  handleEndTimePicked = endTime => {
    this.setState({ selectedEndTime: endTime.toString() });
    this.hideEndTimePicker();
    this.props.setEndTimeFromParent(endTime.toString());
  };

  render() {
    const { isStartTimePickerVisible, isEndTimePickerVisible, selectedStartTime, selectedEndTime } = this.state;

    return (
      <View style={styles.container}>
        <Button title="Start Time" onPress={this.showStartTimePicker} />
        <DateTimePicker
          isVisible={isStartTimePickerVisible}
          onConfirm={this.handleStartTimePicked}
          onCancel={this.hideStartTimePicker}
          mode="time"
        />
        <Button title="End Time" onPress={this.showEndTimePicker} />
        <DateTimePicker
          isVisible={isEndTimePickerVisible}
          onConfirm={this.handleEndTimePicked}
          onCancel={this.hideEndTimePicker}
          mode="time"
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  text: {
    marginVertical: 0
  }
});
