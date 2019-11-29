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
    this.setState({ selectedStartTime: startTime.toLocaleTimeString() });
    this.hideStartTimePicker();
    this.props.setStartTimeFromParent(startTime.toLocaleTimeString());
  };

  handleEndTimePicked = endTime => {
    this.setState({ selectedEndTime: endTime.toLocaleTimeString() });
    this.hideEndTimePicker();
    this.props.setEndTimeFromParent(endTime.toLocaleTimeString());
  };

  render() {
    const { isStartTimePickerVisible, isEndTimePickerVisible, selectedStartTime, selectedEndTime } = this.state;

    return (
      <View>
        <View style={styles.container}>
          <Button title="Start Time" onPress={this.showStartTimePicker} />
          <DateTimePicker
            titleIOS={"Pick a start time"}
            isVisible={isStartTimePickerVisible}
            onConfirm={this.handleStartTimePicked}
            onCancel={this.hideStartTimePicker}
            mode="time"
          />
          <Button title="End Time" onPress={this.showEndTimePicker} />
          <DateTimePicker
            titleIOS={"Pick a end time"}
            isVisible={isEndTimePickerVisible}
            onConfirm={this.handleEndTimePicked}
            onCancel={this.hideEndTimePicker}
            mode="time"
          />
        </View>
        <View style={styles.text}>
          <Text>{this.state.selectedStartTime}</Text>
          <Text>{this.state.selectedEndTime}</Text>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    paddingTop: 3
  },
  text: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    padding: 0
  }
});
